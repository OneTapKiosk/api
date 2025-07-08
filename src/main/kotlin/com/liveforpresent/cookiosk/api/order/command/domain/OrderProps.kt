package com.liveforpresent.cookiosk.api.order.command.domain

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItem
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderStatus
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import java.time.Instant

data class OrderProps (
    val orderItem: MutableSet<OrderItem>,
    val status: OrderStatus,
    val totalPrice: Money,
    val createdAt: Instant,
    val updatedAt: Instant,
    val kioskId: KioskId,
)
