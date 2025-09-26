package com.liveforpresent.onetapkiosk.ordering.order.command.domain

interface OrderCommandRepository {
    fun save(order: Order): Order
    fun findOne(id: Long): Order
}
