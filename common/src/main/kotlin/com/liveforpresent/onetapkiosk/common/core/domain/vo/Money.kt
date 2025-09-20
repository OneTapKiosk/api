package com.liveforpresent.onetapkiosk.common.core.domain.vo

class Money private constructor(val value: Int) {
    companion object {
        fun create(value: Int): Money {
            val money = Money(value)
            money.validate()

            return money
        }
    }

    fun validate() {
        require(value >= 0) { "[Money] 돈은 음수일 수 없습니다." }
    }

    operator fun plus(other: Money): Money = Money(this.value + other.value)
    operator fun minus(other: Money): Money = Money(this.value - other.value)
    operator fun times(quantity: Int): Money = Money(this.value * quantity)

    override fun equals(other: Any?): Boolean = other is Money && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}
