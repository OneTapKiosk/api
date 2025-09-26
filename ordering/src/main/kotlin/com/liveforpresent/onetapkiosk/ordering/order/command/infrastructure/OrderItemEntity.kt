package com.liveforpresent.onetapkiosk.ordering.order.command.infrastructure

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.entity.OrderItem
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.entity.OrderItemProps
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.vo.OrderItemId
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
                productId = orderItem.productId.value
            )
        }

        fun toDomain(orderItemEntity: OrderItemEntity): OrderItem {
            val props = OrderItemProps(
                name = orderItemEntity.name,
                price = Money.create(orderItemEntity.price),
                quantity = orderItemEntity.quantity,
                productId = ProductId(orderItemEntity.productId),
            )

            return OrderItem.create(OrderItemId(orderItemEntity.id), props)
        }
    }
}
