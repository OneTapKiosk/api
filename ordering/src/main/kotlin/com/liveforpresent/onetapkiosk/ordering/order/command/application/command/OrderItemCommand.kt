package com.liveforpresent.onetapkiosk.ordering.order.command.application.command

data class  OrderItemCommand(
    val name: String,
    val price: Int,
    val quantity: Int,
    val productId: Long,
)
