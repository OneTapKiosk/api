package com.liveforpresent.onetapkiosk.user.auth.command.domain

interface AuthCommandRepository {
    fun save(auth: Auth)
    fun findByKioskId(kioskId: Long): Auth
    fun findByKioskIdAndCompanyId(kioskId: Long, companyId: Long): Auth
}
