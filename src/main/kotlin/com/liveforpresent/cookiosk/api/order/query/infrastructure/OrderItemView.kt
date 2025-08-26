package com.liveforpresent.cookiosk.api.order.query.infrastructure

data class OrderItemView(
    val id: Long,
    val name: String,
    val price: Int,
    val quantity: Int
)
