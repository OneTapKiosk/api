package com.liveforpresent.onetapkiosk.common.config.security.jwt

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.Date
import java.util.IllegalFormatException
import javax.crypto.SecretKey

@Service
class JwtTokenProvider(
    jwtProperties: JwtProperties
) {
    private val accessKey: SecretKey = Keys.hmacShaKeyFor(jwtProperties.access.secret.toByteArray())
    private val refreshKey: SecretKey = Keys.hmacShaKeyFor(jwtProperties.refresh.secret.toByteArray())
    private val accessExpiration = jwtProperties.access.expiration
    private val refreshExpiration = jwtProperties.refresh.expiration

    private val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)

    fun createAccessToken(subject: String, claims: Map<String, Any> = emptyMap()): String {
        return createToken(
            subject = subject,
            claims = claims,
            jwtTokenType = JwtTokenType.ACCESS_TOKEN
        )
    }

    fun createRefreshToken(subject: String, claims: Map<String, Any> = emptyMap()): String {
        return createToken(
            subject = subject,
            claims = claims,
            jwtTokenType = JwtTokenType.REFRESH_TOKEN
        )
    }

    fun validateToken(token: String, jwtTokenType: JwtTokenType): Boolean {
        try {
            Jwts.parser().verifyWith(getSecretKey(jwtTokenType)).build().parseSignedClaims(token)
            return true
        } catch (e: Exception) {
            when (e) {
                is SecurityException -> throw CustomException(
                    JwtExceptionCode.JWT_INVALID_SIGNATURE,
                    "[Jwt] 잘못된 Jwt 서명입니다."
                )
                is MalformedJwtException -> throw CustomException(
                    JwtExceptionCode.JWT_INVALID_TOKEN,
                    "[Jwt] 유효하지 않은 Jwt 토큰 형식입니다."
                )
                is ExpiredJwtException -> throw CustomException(
                    JwtExceptionCode.JWT_EXPIRED,
                    "[Jwt] 만료된 Jwt 토큰 입니다."
                )
                is UnsupportedJwtException -> throw CustomException(
                    JwtExceptionCode.JWT_UNSUPPORTED,
                    "[Jwt] 지원되지 않는 Jwt 토큰 입니다."
                )
                is IllegalFormatException -> throw CustomException(
                    JwtExceptionCode.JWT_CLAIM_EMPTY,
                    "[Jwt] Claim 문자열이 비어 있습니다."
                )
                else -> {
                    logger.error("Unexpected JWT validation exception: ${e.message}", e)
                    throw CustomException(
                        JwtExceptionCode.JWT_UNEXPECTED_EXCEPTION,
                        e
                    )
                }
            }
        }
    }

    private fun createToken(subject: String, claims: Map<String, Any>, jwtTokenType: JwtTokenType): String {
        val now = Date()
        val expiration = Date(now.time + getExpiration(jwtTokenType))

        return Jwts.builder()
            .claims(claims)
            .subject(subject)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(getSecretKey(jwtTokenType), Jwts.SIG.HS256)
            .compact()
    }

    private fun getExpiration(jwtTokenType: JwtTokenType): Long {
        return when (jwtTokenType) {
            JwtTokenType.ACCESS_TOKEN -> accessExpiration
            JwtTokenType.REFRESH_TOKEN -> refreshExpiration
        }
    }

    private fun getSecretKey(jwtTokenType: JwtTokenType): SecretKey {
        return when (jwtTokenType) {
            JwtTokenType.ACCESS_TOKEN -> accessKey
            JwtTokenType.REFRESH_TOKEN -> refreshKey
        }
    }

    private fun extractClaims(token: String, jwtTokenType: JwtTokenType): Claims {
        val parser = Jwts.parser()
            .verifyWith(getSecretKey(jwtTokenType))
            .build()

        return parser
            .parseSignedClaims(token)
            .payload
    }
}
