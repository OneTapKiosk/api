package com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode

class KioskStatus private constructor(val value: String) {
    companion object {
        val ONLINE = KioskStatus("ONLINE")
        val OFFLINE = KioskStatus("OFFLINE")
        val ERROR = KioskStatus("ERROR")

        val allowedStatus = setOf(ONLINE, OFFLINE, ERROR)

        fun create (value: String): KioskStatus {
            val kioskStatus = KioskStatus(value)
            kioskStatus.validate()

            return kioskStatus
        }
    }

    fun validate() {
        require(allowedStatus.firstOrNull { it.value == value.uppercase() } != null) {
            throw CustomException(
                CustomExceptionCode.KIOSK_STATUS_INVALID_STATUS,
                "[KioskStatus] 유효하지 않은 키오스크 상태 입니다."
            )
        }
    }

    override fun equals(other: Any?): Boolean = other is KioskStatus && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}