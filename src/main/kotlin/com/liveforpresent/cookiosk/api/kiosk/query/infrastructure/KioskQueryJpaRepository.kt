package com.liveforpresent.cookiosk.api.kiosk.query.infrastructure

import com.liveforpresent.cookiosk.api.kiosk.command.infrastructure.KioskEntity
import com.liveforpresent.cookiosk.api.kiosk.query.domain.KioskModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface KioskQueryJpaRepository: JpaRepository<KioskEntity, Long> {
    fun findByIdAndIsDeletedFalse(id: Long): Optional<KioskModel>
    fun findByCompanyIdAndIsDeletedFalse(companyId: Long): List<KioskModel>
}
