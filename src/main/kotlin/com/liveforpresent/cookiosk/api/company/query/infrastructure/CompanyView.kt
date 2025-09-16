package com.liveforpresent.cookiosk.api.company.query.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.time.Instant

@Entity
@Immutable
@Table(name = "vw_company")
class CompanyView(
    @Id
    val id: Long,
    val registrationNumber: String,
    val phone: String,
    val email: String,
    val createdAt: Instant,
    val updatedAt: Instant
)
