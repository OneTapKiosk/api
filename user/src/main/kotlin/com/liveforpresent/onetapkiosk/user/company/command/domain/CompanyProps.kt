package com.liveforpresent.onetapkiosk.user.company.command.domain

import com.liveforpresent.onetapkiosk.user.company.command.domain.vo.RegistrationNumber
import java.time.Instant

data class CompanyProps(
    val registrationNumber: RegistrationNumber,
    val phone: String,
    val email: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val isDeleted: Boolean,
    val deletedAt: Instant?
)
