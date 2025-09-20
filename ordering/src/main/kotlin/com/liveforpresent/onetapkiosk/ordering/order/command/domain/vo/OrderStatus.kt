package com.liveforpresent.onetapkiosk.ordering.order.command.domain.vo

class OrderStatus(val value: String) {
    companion object {
        val CREATED = OrderStatus("CREATED")
        val PENDING = OrderStatus("PENDING")
        val REJECTED = OrderStatus("REJECTED")
        val CANCELLED = OrderStatus("CANCELLED")
        val SUCCESS = OrderStatus("SUCCESS")

        val allowedStatus = setOf(CREATED, PENDING, REJECTED, CANCELLED, SUCCESS)
        val failStatus = setOf(REJECTED, CANCELLED)

        fun create(value: String): OrderStatus {
            val orderStatus = OrderStatus(value)
            orderStatus.validate()

            return orderStatus
        }
    }

    fun validate() {
        require(allowedStatus.firstOrNull { it.value == value.uppercase() } != null) {
            "[OrderStatus]유효하지 않은 주문 상태 입니다."
        }
    }

    fun canTransitionTo(nextState: OrderStatus): Boolean {
        return when (this) {
            CREATED -> nextState in setOf(PENDING, REJECTED, CANCELLED)
            PENDING -> nextState in setOf(SUCCESS, REJECTED, CANCELLED)
            SUCCESS, REJECTED, CANCELLED -> false
            else -> false
        }
    }

    override fun equals(other: Any?): Boolean = other is OrderStatus && this.value == other.value
    override fun hashCode(): Int = value.hashCode()
}
