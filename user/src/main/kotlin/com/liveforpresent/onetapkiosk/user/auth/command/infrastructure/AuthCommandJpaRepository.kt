package com.liveforpresent.onetapkiosk.user.auth.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AuthCommandJpaRepository: JpaRepository<AuthEntity, Long> {
    fun findByKioskId(kioskId: Long): Optional<AuthEntity>
    fun findByKioskIdAndCompanyId(kioskId: Long, companyId: Long): Optional<AuthEntity>
}
