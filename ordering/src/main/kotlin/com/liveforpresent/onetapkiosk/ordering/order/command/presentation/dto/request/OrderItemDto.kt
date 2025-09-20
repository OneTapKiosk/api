package com.liveforpresent.onetapkiosk.ordering.order.command.presentation.dto.request

data class OrderItemDto (
    val name: String,
    val price: Int,
    val quantity: Int,
    val productId: String,
)
