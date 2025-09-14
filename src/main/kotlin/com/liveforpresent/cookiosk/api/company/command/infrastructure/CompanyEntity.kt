package com.liveforpresent.cookiosk.api.company.command.infrastructure

import com.liveforpresent.cookiosk.api.company.command.domain.Company
import com.liveforpresent.cookiosk.api.company.command.domain.CompanyProps
import com.liveforpresent.cookiosk.api.company.command.domain.vo.CompanyId
import com.liveforpresent.cookiosk.api.company.command.domain.vo.RegistrationNumber
import jakarta.persistence.Column
import jakarta.persistence.Id
import java.time.Instant

class CompanyEntity(
    @Id
    val id: Long,

    @Column(nullable = false)
    val registrationNumber: String,

    @Column(nullable = false)
    val phone: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val createdAt: Instant,

    @Column(nullable = false)
    val isDeleted: Boolean,

    @Column
    val deletedAt: Instant?
) {
    companion object {
        fun toPersistence(company: Company): CompanyEntity {
            return CompanyEntity(
                id = company.id.value,
                registrationNumber = company.registrationNumber.value,
                phone = company.phone,
                email = company.email,
                createdAt = company.createdAt,
                isDeleted = company.isDeleted,
                deletedAt = company.deletedAt
            )
        }

        fun toDomain(companyEntity: CompanyEntity): Company {
            val companyProps = CompanyProps(
                registrationNumber = RegistrationNumber.create(companyEntity.registrationNumber),
                phone = companyEntity.phone,
                email = companyEntity.email,
                createdAt = companyEntity.createdAt,
                isDeleted = companyEntity.isDeleted,
                deletedAt = companyEntity.deletedAt
            )

            return Company.create(CompanyId(companyEntity.id), companyProps)
        }
    }
}
