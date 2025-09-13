package com.liveforpresent.cookiosk.api.order.command.presentation.dto.request

import com.liveforpresent.cookiosk.api.order.command.application.command.CreateOrderCommand
import com.liveforpresent.cookiosk.api.order.command.application.command.OrderItemCommand

data class CreateOrderReqDto(
    val orderItems: MutableSet<OrderItemDto>,
    val kioskId: Long
) {
    fun toCommand(): CreateOrderCommand {
        val orderItems = orderItems.map {
            OrderItemCommand(it.name, it.price, it.quantity, it.productId.toLong())
        }.toMutableSet()

        return CreateOrderCommand(orderItems, kioskId)
    }
}
