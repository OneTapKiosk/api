package com.liveforpresent.onetapkiosk.user.auth.command.application.handler

import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.AuthId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.infrastructure.util.SnowflakeIdUtil
import com.liveforpresent.onetapkiosk.user.auth.command.application.command.SignUpCommand
import com.liveforpresent.onetapkiosk.user.auth.command.domain.Auth
import com.liveforpresent.onetapkiosk.user.auth.command.domain.AuthCommandRepository
import com.liveforpresent.onetapkiosk.user.auth.command.domain.AuthProps
import com.liveforpresent.onetapkiosk.user.auth.command.domain.vo.PasswordHash
import com.liveforpresent.onetapkiosk.user.auth.command.infrastructure.PasswordHashingProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class SignUpHandler(
    private val authCommandRepository: AuthCommandRepository,
    private val passwordHashingProvider: PasswordHashingProvider
) {
    @Transactional
    fun execute(command: SignUpCommand) {
        val passwordHash = passwordHashingProvider.hash(command.password)

        val authProps = AuthProps(
            passwordHash = PasswordHash.create(passwordHash),
            companyId = CompanyId(command.companyId),
            kioskId = KioskId(command.kioskId),
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            isDeleted = false,
            deletedAt = null
        )
        val auth = Auth.create(AuthId(SnowflakeIdUtil.generateId()), authProps)

        authCommandRepository.save(auth)
    }
}
