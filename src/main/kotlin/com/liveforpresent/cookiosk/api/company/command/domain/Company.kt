package com.liveforpresent.cookiosk.api.company.command.domain

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

    val registrationNumber: RegistrationNumber get() = props.registrationNumber
    val phone: String get() = props.phone
    val email: String get() = props.email
    val createdAt: Instant get() = props.createdAt
    val isDeleted: Boolean get() = props.isDeleted
    val deletedAt: Instant? get() = props.deletedAt
}
