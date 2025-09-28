package com.liveforpresent.onetapkiosk.user.auth.command.infrastructure

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.user.auth.command.domain.Auth
import com.liveforpresent.onetapkiosk.user.auth.command.domain.AuthCommandRepository
import com.liveforpresent.onetapkiosk.user.shared.exception.AuthExceptionCode
import org.springframework.stereotype.Repository

@Repository
class AuthCommandRepositoryImpl(
    private val authCommandJpaRepository: AuthCommandJpaRepository
): AuthCommandRepository {
    override fun save(auth: Auth) {
        val authEntity = AuthEntity.toPersistence(auth)
        authCommandJpaRepository.save(authEntity)
    }

    override fun findByKioskId(kioskId: Long): Auth {
        val authEntity = authCommandJpaRepository.findByKioskId(kioskId)
        .orElseThrow { CustomException(
            AuthExceptionCode.AUTH_NOT_FOUND,
            "[Auth] 해당 유저의 인증 정보가 존재하지 않습니다."
        ) }

        return AuthEntity.toDomain(authEntity)
    }

    override fun findByKioskIdAndCompanyId(kioskId: Long, companyId: Long): Auth {
        val authEntity = authCommandJpaRepository.findByKioskIdAndCompanyId(kioskId, companyId)
            .orElseThrow { throw CustomException(
                AuthExceptionCode.AUTH_NOT_FOUND,
                "[Auth] 해당 유저의 인증 정보가 존재하지 않습니다."
            ) }

        return AuthEntity.toDomain(authEntity)
    }
}
