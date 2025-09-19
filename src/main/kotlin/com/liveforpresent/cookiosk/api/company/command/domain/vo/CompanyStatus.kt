package com.liveforpresent.cookiosk.api.company.command.domain.vo

import com.liveforpresent.cookiosk.shared.exception.CustomException
import com.liveforpresent.cookiosk.shared.exception.CustomExceptionCode

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
        require(CompanyStatus.allowedStatus.firstOrNull { it.value == value.uppercase() } != null) {
            throw CustomException(
                CustomExceptionCode.COMPANY_INVALID_STATUS,
                "[CompanyStatus] 유효하지 않은 사업자 상태 입니다."
            )
        }
    }

    override fun equals(other: Any?): Boolean = other is CompanyStatus && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}
