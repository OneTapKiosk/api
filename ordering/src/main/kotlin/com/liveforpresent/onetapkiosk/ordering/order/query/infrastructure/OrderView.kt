package com.liveforpresent.onetapkiosk.ordering.order.query.infrastructure

import com.liveforpresent.onetapkiosk.ordering.order.query.infrastructure.converter.OrderItemListConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.time.Instant

@Entity
@Immutable
@Table(name = "vw_orders")
class OrderView(
    @Id
    val id: Long,
    val status: String,
    val totalPrice: Int,
    val kioskId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,

    @Convert(converter = OrderItemListConverter::class)
    val orderItems: List<OrderItemView>
)
