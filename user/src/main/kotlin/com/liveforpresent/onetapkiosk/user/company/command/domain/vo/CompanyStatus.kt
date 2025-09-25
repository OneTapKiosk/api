package com.liveforpresent.onetapkiosk.user.company.command.domain.vo

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.user.shared.exception.CompanyExceptionCode

class CompanyStatus private constructor(val value: String) {
    companion object {
        val ACTIVE = CompanyStatus("ACTIVE")
        val INACTIVE = CompanyStatus("INACTIVE")
        val EXPIRED = CompanyStatus("EXPIRED")

        val allowedStatus = setOf(ACTIVE, INACTIVE, EXPIRED)

        fun create(value: String): CompanyStatus {
            val companyStatus = CompanyStatus(value)
            companyStatus.validate()

            return companyStatus
        }
    }

    fun validate() {
        require(allowedStatus.firstOrNull { it.value == value.uppercase() } != null) {
            throw CustomException(
                CompanyExceptionCode.COMPANY_INVALID_STATUS,
                "[CompanyStatus] 유효하지 않은 사업자 상태 입니다."
            )
        }
    }

    override fun equals(other: Any?): Boolean = other is CompanyStatus && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}
