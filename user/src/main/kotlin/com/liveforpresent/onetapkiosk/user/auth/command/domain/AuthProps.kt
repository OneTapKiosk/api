package com.liveforpresent.onetapkiosk.user.auth.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.user.auth.command.domain.vo.PasswordHash
import java.time.Instant

data class AuthProps(
    val passwordHash: PasswordHash,
    val companyId: CompanyId,
    val kioskId: KioskId,
    val createdAt: Instant,
    val updatedAt: Instant,
    val isDeleted: Boolean,
    val deletedAt: Instant?
)
