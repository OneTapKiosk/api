package com.liveforpresent.cookiosk.api.company.command.domain

import com.liveforpresent.cookiosk.api.company.command.domain.event.CompanyCreatedEvent
import com.liveforpresent.cookiosk.api.company.command.domain.event.CompanyDeletedEvent
import com.liveforpresent.cookiosk.api.company.command.domain.event.CompanyUpdatedEvent
import com.liveforpresent.cookiosk.api.company.command.domain.vo.CompanyId
import com.liveforpresent.cookiosk.api.company.command.domain.vo.RegistrationNumber
import com.liveforpresent.cookiosk.shared.core.domain.AggregateRoot
import com.liveforpresent.cookiosk.shared.exception.CustomException
import com.liveforpresent.cookiosk.shared.exception.CustomExceptionCode
import java.time.Instant

class Company private constructor(
    id: CompanyId,
    private val props: CompanyProps
): AggregateRoot<CompanyId>(id) {
    companion object {
        fun create(id: CompanyId, props: CompanyProps): Company {
            val company = Company(id, props)
            company.validate()

            company.addDomainEvent(CompanyCreatedEvent())

            return company
        }
    }

    fun validate() {
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()

        require(emailRegex.matches(props.email)) { throw CustomException(
            CustomExceptionCode.COMPANY_INVALID_EMAIL,
            "[Company] 유효하지 않은 이메일 입니다."
        ) }
    }

    fun update(
        newRegistrationNumber: RegistrationNumber,
        newPhone: String,
        newEmail: String
    ): Company {
        val updatedCompany = Company(id, props.copy(
            registrationNumber = newRegistrationNumber,
            phone = newPhone,
            email = newEmail,
            updatedAt = Instant.now()
        ))

        updatedCompany.validate()

        updatedCompany.addDomainEvent(CompanyUpdatedEvent())

        return updatedCompany
    }

    fun delete(): Company {
        val updatedCompany = Company(id, props.copy(
            isDeleted = true,
            deletedAt = Instant.now()
        ))

        updatedCompany.addDomainEvent(CompanyDeletedEvent())

        return updatedCompany
    }

    fun unDelete(): Company {
        val updatedCompany = Company(id, props.copy(
            isDeleted = false,
            deletedAt = null
        ))

        return updatedCompany
    }

    val registrationNumber: RegistrationNumber get() = props.registrationNumber
    val phone: String get() = props.phone
    val email: String get() = props.email
    val createdAt: Instant get() = props.createdAt
    val updatedAt: Instant get() = props.updatedAt
    val isDeleted: Boolean get() = props.isDeleted
    val deletedAt: Instant? get() = props.deletedAt
}
