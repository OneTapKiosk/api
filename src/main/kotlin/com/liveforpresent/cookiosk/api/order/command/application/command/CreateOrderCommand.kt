package com.liveforpresent.cookiosk.api.order.command.application.command

data class CreateOrderCommand(
    val orderItems: MutableSet<OrderItemCommand>,
    val kioskId: Long
)
