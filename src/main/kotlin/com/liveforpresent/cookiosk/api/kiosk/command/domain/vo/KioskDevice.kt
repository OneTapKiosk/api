package com.liveforpresent.cookiosk.api.kiosk.command.domain.vo

class KioskDevice private constructor(val value: String) {
    companion object {
        val BARCODE_READER = KioskDevice("BARCODE_READER")
        val CASH_READER = KioskDevice("CASH_READER")
        val CARD_READER = KioskDevice("CARD_READER")

        val allowedDevice = setOf(BARCODE_READER, CASH_READER, CARD_READER)

        fun create(value: String): KioskDevice {
            val kioskDevice = KioskDevice(value)
            kioskDevice.validate()

            return kioskDevice
        }
    }

    fun validate() {
        require(allowedDevice.firstOrNull { it.value == value.uppercase() } != null) { "[KioskDevice] 유효하지 않은 키오스크 주변 장치입니다." }
    }

    override fun equals(other: Any?): Boolean = other is KioskDevice && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}