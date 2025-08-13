package com.liveforpresent.cookiosk.api.order.query.domain

import java.time.Instant

data class OrderDetailModel(
    val id: String,
    val status: String,
    val totalPrice: Int,
    val kioskId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
    val orderItems: List<OrderItemModel>,
)
