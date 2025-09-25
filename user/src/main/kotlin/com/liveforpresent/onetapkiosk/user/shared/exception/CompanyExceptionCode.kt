package com.liveforpresent.onetapkiosk.user.shared.exception

import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import org.springframework.http.HttpStatus

enum class CompanyExceptionCode(
    override val code: HttpStatus,
    override val message: String
): CustomExceptionCode {
    COMPANY_REGISTRATION_NUMBER_EMPTY(HttpStatus.BAD_REQUEST, "[CompanyRegistrationNumber] 사업자등록번호는 필수 입니다."),
    COMPANY_INVALID_REGISTRATION_NUMBER(HttpStatus.BAD_REQUEST, "[CompanyRegistrationNumber] 유효하지 않은 사업자등록번호 형식입니다."),
    COMPANY_INVALID_STATUS(HttpStatus.BAD_REQUEST, "[CompanyStatus] 유효하지 않은 사업자 상태 입니다."),
    COMPANY_INVALID_EMAIL(HttpStatus.BAD_REQUEST, "[Company] 유효하지 않은 이메일 입니다."),
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "[CompanyRepository] 해당 사업체는 존재하지 않습니다."),
    COMPANY_ALREADY_EXISTS(HttpStatus.CONFLICT, "[Company] 해당 사업자 등록번호로 이미 가입된 사업체가 있습니다.")
}
