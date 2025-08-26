package com.liveforpresent.cookiosk.api.order.command.domain

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItem
import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderCreatedEvent
import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderFinishedAsRejectEvent
import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderFinishedAsSuccessEvent
import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderProcessedToPaymentEvent
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
            val temp = Order(id, props)
            val order = temp.calculateTotalPrice()
            order.validate()
            order.addDomainEvent(OrderCreatedEvent())

            return order
        }
    }

    fun validate() {}

    private fun calculateTotalPrice(): Order {
        return Order(id, props.copy(totalPrice = props.orderItems
            .sumOf { it.price.times(it.quantity).value }
            .let { Money.create(it) }))
    }

    fun processPayment(): Order {
        require(props.status.canTransitionTo(OrderStatus.PENDING)) { "[Order] ${props.status.value} 상태에서는 결제 단계로 넘어갈 수 없습니다." }

        val order = Order(id, props.copy(
            status = OrderStatus.PENDING,
            updatedAt = Instant.now()
        ))

        order.addDomainEvent(OrderProcessedToPaymentEvent())

        return order
    }

    fun finishAsSuccess(): Order {
        require(props.status.canTransitionTo(OrderStatus.SUCCESS)) {
            "[Order] ${props.status.value} 상태에서는 주문 성공이 불가능합니다."
        }

        val updatedOrder =  Order(id, props.copy(
            status = OrderStatus.SUCCESS,
            updatedAt = Instant.now()
        ))

        updatedOrder.addDomainEvent(OrderFinishedAsSuccessEvent(
            saleItems = props.orderItems,
            totalPrice = props.totalPrice.value,
            kioskId = kioskId.value
        ))

        return updatedOrder
    }

    fun finishAsReject(): Order {
        require(props.status.canTransitionTo(OrderStatus.REJECTED)) {
            "[Order] ${props.status.value} 상태에서는 주문 실패가 불가능합니다."
        }

        val order = Order(id, props.copy(
            status = OrderStatus.REJECTED,
            updatedAt = Instant.now()
        ))

        order.addDomainEvent(OrderFinishedAsRejectEvent())

        return order
    }

    fun finishAsCancel(): Order {
        require(props.status.canTransitionTo(OrderStatus.CANCELLED)) {
            "[Order] ${props.status.value} 상태에서는 주문 취소가 불가능합니다."
        }

        val order =  Order(id, props.copy(
            status = OrderStatus.CANCELLED,
            updatedAt = Instant.now()
        ))

        order.addDomainEvent(OrderProcessedToPaymentEvent())

        return order
    }

    val orderItems: MutableSet<OrderItem> get() = props.orderItems
    val orderStatus: OrderStatus get() = props.status
    val totalPrice: Money get() = props.totalPrice
    val createdAt: Instant get() = props.createdAt
    val updatedAt: Instant get() = props.updatedAt
    val kioskId: KioskId get() = props.kioskId
}
