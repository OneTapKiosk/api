package com.liveforpresent.onetapkiosk.ordering.order.query.domain

data class OrderItemModel(
    val id: String,
    val name: String,
    val price: Int,
    val quantity: Int
)
