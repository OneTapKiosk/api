package com.liveforpresent.cookiosk.api.company.command.domain.vo

import com.liveforpresent.cookiosk.shared.exception.CustomException
import com.liveforpresent.cookiosk.shared.exception.CustomExceptionCode

class RegistrationNumber private constructor(val value: String) {
    companion object {
        fun create(value: String): RegistrationNumber {
            val registrationNumber = RegistrationNumber(value)
            registrationNumber.validate()

            return registrationNumber
        }
    }

    fun validate() {
        require(value.isNotEmpty()) { throw CustomException(
            CustomExceptionCode.COMPANY_REGISTRATION_NUMBER_EMPTY,
            "[CompanyRegistrationNumber] 사업자등록번호는 필수 입니다."
        ) }
        require(value.length == 10) { throw CustomException(
            CustomExceptionCode.COMPANY_INVALID_REGISTRATION_NUMBER,
            "[CompanyRegistrationNumber] 유효하지 않은 사업자등록번호 형식입니다."
        )}
    }

    override fun equals(other: Any?): Boolean = other is RegistrationNumber && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}
