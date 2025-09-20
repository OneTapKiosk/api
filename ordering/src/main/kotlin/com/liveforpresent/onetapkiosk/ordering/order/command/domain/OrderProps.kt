package com.liveforpresent.onetapkiosk.ordering.order.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.entity.OrderItem
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.vo.OrderStatus
import java.time.Instant

data class OrderProps (
    val orderItems: MutableSet<OrderItem>,
    val status: OrderStatus,
    val totalPrice: Money,
    val createdAt: Instant,
    val updatedAt: Instant,
    val kioskId: KioskId,
)
