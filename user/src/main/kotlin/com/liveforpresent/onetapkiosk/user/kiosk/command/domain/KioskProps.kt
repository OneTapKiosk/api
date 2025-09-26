package com.liveforpresent.onetapkiosk.user.kiosk.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo.KioskDevice
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo.KioskStatus
import java.time.Instant

data class KioskProps(
    val name: String,
    val location: String,
    val status: KioskStatus,
    val version: String,
    val devices: MutableSet<KioskDevice>,
    val companyId: CompanyId,
    val isDeleted: Boolean = false,
    val deletedAt: Instant? = null
)
