package com.liveforpresent.cookiosk.api.kiosk.query.domain

interface KioskQueryRepository {
    fun findById(id: Long): KioskModel
    fun findByCompanyId(companyId: Long): List<KioskModel>
}
