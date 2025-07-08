package com.liveforpresent.cookiosk.api.kiosk.command.domain.vo

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
        require(allowedStatus.firstOrNull { it.value == value.uppercase() } != null) { "[KioskStatus] 유효하지 않은 키오스크 상태 입니다." }
    }
}