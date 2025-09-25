package com.liveforpresent.onetapkiosk.ordering.order.command.domain.vo

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.ordering.shared.exception.OrderExceptionCode

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
            throw CustomException(
                OrderExceptionCode.ORDER_STATUS_INVALID_STATE,
                "[OrderStatus] 주문 상태가 유효하지 않습니다."
            )
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
