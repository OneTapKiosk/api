package com.liveforpresent.onetapkiosk.user.shared.exception

import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import org.springframework.http.HttpStatus

enum class KioskExceptionCode(
    override val code: HttpStatus,
    override val message: String,
): CustomExceptionCode {
    KIOSK_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[Kiosk] 키오스크명은 필수 입니다."),
    KIOSK_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[Kiosk] 키오스크명은 최대 31자 입니다."),
    KIOSK_NOT_FOUND(HttpStatus.NOT_FOUND, "[KioskRepository] 해당 키오스크가 존재하지 않습니다."),

    KIOSK_DEVICE_INVALID_DEVICE(HttpStatus.BAD_REQUEST, "[KioskDevice] 유효하지 않은 키오스크 주변 장치입니다."),

    KIOSK_STATUS_INVALID_STATUS(HttpStatus.BAD_REQUEST, "[KioskStatus] 유효하지 않은 키오스크 상태 입니다."),
}
