package com.liveforpresent.onetapkiosk.user.kiosk.query.domain

interface KioskQueryRepository {
    fun findById(id: Long): KioskModel
    fun findByCompanyId(companyId: Long): List<KioskModel>
    fun refreshView()
}
