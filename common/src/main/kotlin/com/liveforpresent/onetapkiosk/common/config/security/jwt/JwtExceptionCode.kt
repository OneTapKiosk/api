package com.liveforpresent.onetapkiosk.common.config.security.jwt

import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import org.springframework.http.HttpStatus

enum class JwtExceptionCode(
    override val code: HttpStatus,
    override val message: String
): CustomExceptionCode {
    JWT_INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED, "[Jwt] 잘못된 Jwt 서명입니다."),
    JWT_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "[Jwt] 유효하지 않은 Jwt 토큰 형식입니다."),
    JWT_EXPIRED(HttpStatus.UNAUTHORIZED, "[Jwt] 만료된 Jwt 토큰 입니다."),
    JWT_UNSUPPORTED(HttpStatus.UNAUTHORIZED, "[Jwt] 지원되지 않는 Jwt 토큰 입니다."),
    JWT_CLAIM_EMPTY(HttpStatus.UNAUTHORIZED, "[Jwt] Claim 문자열이 비어 있습니다."),
    JWT_UNEXPECTED_EXCEPTION(HttpStatus.UNAUTHORIZED, "[Jwt] UnexpectedException.")
}
