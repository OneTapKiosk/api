package com.liveforpresent.cookiosk.api.company.query.domain

import java.time.Instant

data class CompanyModel(
    val id: String,
    val registrationNumber: String,
    val phone: String,
    val email: String,
    val createdAt: Instant,
    val updatedAt: Instant
)
