package com.liveforpresent.cookiosk.api.kiosk.command.domain

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.CompanyId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskDevice
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskStatus

data class KioskProps(
    val name: String,
    val location: String,
    val status: KioskStatus,
    val version: String,
    val devices: MutableSet<KioskDevice>,
    val companyId: CompanyId,
)
