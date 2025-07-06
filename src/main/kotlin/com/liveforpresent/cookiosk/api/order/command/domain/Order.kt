package com.liveforpresent.cookiosk.api.order.command.domain

import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItem
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderId
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderStatus
import com.liveforpresent.cookiosk.shared.core.domain.AggregateRoot
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import java.time.Instant

class Order private constructor(
    id: OrderId,
    private val props: OrderProps
): AggregateRoot<OrderId>(id) {
    companion object {
        fun create(id: OrderId, props: OrderProps): Order {
            val order = Order(id, props)
            order.validate()

            return order
        }
    }

    fun validate() {}

    fun processPayment(): Order {
        require(props.status.canTransitionTo(OrderStatus.PENDING)) { "[Order] ${props.status.value} 상태에서는 결제 단계로 넘어갈 수 없습니다." }

        return Order(id, props.copy(
            status = OrderStatus.PENDING,
            updatedAt = Instant.now()
        ))
    }

    fun finishAsSuccess(): Order {
        require(props.status.canTransitionTo(OrderStatus.SUCCESS)) {
            "[Order] ${props.status.value} 상태에서는 주문 성공이 불가능합니다."
        }

        return Order(id, props.copy(
            status = OrderStatus.SUCCESS,
            updatedAt = Instant.now()
        ))
    }

    fun finishAsFail(status: OrderStatus): Order {
        require(status == OrderStatus.REJECTED || status == OrderStatus.CANCELLED) {
            "[Order] 주문에 실패 했습니다. (${props.status.value})"
        }

        return Order(id, props.copy(
            status = status,
            updatedAt = Instant.now()
        ))
    }

    val orderItems: MutableSet<OrderItem> get() = props.orderItem
    val orderStatus: OrderStatus get() = props.status
    val totalPrice: Money get() = props.totalPrice
    val createdAt: Instant get() = props.createdAt
    val updatedAt: Instant get() = props.updatedAt
}
