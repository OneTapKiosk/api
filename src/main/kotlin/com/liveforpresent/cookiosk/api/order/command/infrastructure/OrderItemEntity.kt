package com.liveforpresent.cookiosk.api.order.command.infrastructure

import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItem
import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItemProps
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderItemId
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "order_item")
class OrderItemEntity(
    @Id
    @Column(nullable = false)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val quantity: Int,

    @Column(nullable = false)
    val productId: Long
) {
    companion object {
        fun toPersistence(orderItem: OrderItem): OrderItemEntity {
            return OrderItemEntity(
                id = orderItem.id.value,
                name = orderItem.name,
                price = orderItem.price.value,
                quantity = orderItem.quantity,
                productId = orderItem.pro
            )
        }

        fun toDomain(orderItemEntity: OrderItemEntity): OrderItem {
            val props = OrderItemProps(
                name = orderItemEntity.name,
                price = Money.create(orderItemEntity.price),
                quantity = orderItemEntity.quantity
            )

            return OrderItem.create(OrderItemId(orderItemEntity.id), props)
        }
    }
}
