package com.liveforpresent.onetapkiosk.common.core.domain.vo

class Barcode private constructor(val value: String) {
    companion object {
        fun create(value: String): Barcode {
            val barcode = Barcode(value)
            barcode.validate()

            return barcode
        }
    }

    fun validate() {
        require(value.matches(Regex("\\d{13}"))) { "[Barcode] 바코드는 13자리 숫자여야 합니다 (EAN-13 형식)." }
    }

    override fun equals(other: Any?): Boolean = other is Barcode && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}
