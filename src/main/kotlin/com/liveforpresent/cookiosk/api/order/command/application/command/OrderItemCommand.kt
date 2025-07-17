package com.liveforpresent.cookiosk.api.order.command.application.command

data class  OrderItemCommand(
    val name: String,
    val price: Int,
    val quantity: Int,
)
