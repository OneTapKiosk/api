package com.liveforpresent.onetapkiosk.ordering.order.query.infrastructure

data class OrderItemView(
    val id: Long,
    val name: String,
    val price: Int,
    val quantity: Int
)
