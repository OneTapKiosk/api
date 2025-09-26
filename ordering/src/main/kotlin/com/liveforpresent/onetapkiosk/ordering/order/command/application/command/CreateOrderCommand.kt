package com.liveforpresent.onetapkiosk.ordering.order.command.application.command

data class CreateOrderCommand(
    val orderItems: MutableSet<OrderItemCommand>,
    val kioskId: Long
)
