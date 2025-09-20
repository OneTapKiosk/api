package com.liveforpresent.onetapkiosk.user.kiosk.query.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface KioskQueryJpaRepository: JpaRepository<KioskView, Long> {
    fun findByCompanyId(companyId: Long): List<KioskView>
}
