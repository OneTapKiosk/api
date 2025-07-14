package com.liveforpresent.cookiosk.api.order.command.infrastructure

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.order.command.domain.Order
import com.liveforpresent.cookiosk.api.order.command.domain.OrderProps
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderId
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderStatus
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "orders")
class OrderEntity(
    @Id
    @Column(nullable = false)
    val id: Long,

    @Column(nullable = false)
    val status: String,

    @Column(nullable = false)
    val totalPrice: Int,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "order_item_id")
    val orderItems: MutableSet<OrderItemEntity>,

    @Column(nullable = false)
    val kioskId: Long,

    @Column(nullable = false)
    val createdAt: Instant,

    @Column(nullable = false)
    val updatedAt: Instant,
) {
    companion object {
        fun toPersistence(order: Order): OrderEntity {
            return OrderEntity(
                id = order.id.value,
                status = order.orderStatus.value,
                totalPrice = order.totalPrice.value,
                orderItems = order.orderItems.map { orderItem -> OrderItemEntity.toPersistence(orderItem) }.toMutableSet(),
                kioskId = order.kioskId.value,
                createdAt = order.createdAt,
                updatedAt = order.updatedAt,
            )
        }

        fun toDomain(orderEntity: OrderEntity): Order {
            val props = OrderProps(
                status = OrderStatus.create(orderEntity.status),
                totalPrice = Money.create(orderEntity.totalPrice),
                orderItems = orderEntity.orderItems.map { orderItem -> OrderItemEntity.toDomain(orderItem) }.toMutableSet(),
                kioskId = KioskId(orderEntity.kioskId),
                createdAt = orderEntity.createdAt,
                updatedAt = orderEntity.updatedAt,
            )

            return Order.create(OrderId(orderEntity.id), props)
        }
    }
}
