package com.liveforpresent.onetapkiosk.user.auth.command.domain.vo

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.user.auth.command.infrastructure.PasswordHashingProvider
import com.liveforpresent.onetapkiosk.user.shared.exception.AuthExceptionCode

class PasswordHash private constructor(val value: String) {
    companion object {
        private lateinit var hasher: PasswordHashingProvider

        fun create(value: String): PasswordHash {
            val passwordHash = PasswordHash(value)
            passwordHash.validate()

            return passwordHash
        }

        fun fromPlain(plainPassword: String): PasswordHash {
            validatePlainPassword(plainPassword)

            val hashedPassword = hasher.hash(plainPassword)

            val passwordHash = PasswordHash(hashedPassword)

            passwordHash.validate()

            return passwordHash
        }

        fun initializeHasher(passwordHashingProvider: PasswordHashingProvider) {
            hasher = passwordHashingProvider
        }

        private fun validatePlainPassword(plainPassword: String) {
            require(plainPassword.length >= 6) { throw CustomException(
                AuthExceptionCode.AUTH_INVALID_PASSWORD_LENGTH,
                "[Auth] 비밀번호는 6자 이상이어야 합니다."
            ) }
        }
    }

    fun validate() {}

    fun matches(plainPassword: String): Boolean {
        return hasher.check(plainPassword, value)
    }

    override fun equals(other: Any?): Boolean = other is PasswordHash && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}
