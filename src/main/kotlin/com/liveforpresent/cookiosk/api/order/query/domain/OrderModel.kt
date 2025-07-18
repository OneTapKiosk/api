package com.liveforpresent.cookiosk.api.order.query.domain

import java.time.Instant

data class OrderModel(
    val id: Long,
    val status: String,
    val totalPrice: Int,
    val kioskId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
)
