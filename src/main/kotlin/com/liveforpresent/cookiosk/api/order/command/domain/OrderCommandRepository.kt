package com.liveforpresent.cookiosk.api.order.command.domain

interface OrderCommandRepository {
    fun save(order: Order): Order
}
