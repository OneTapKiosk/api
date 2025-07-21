package com.liveforpresent.cookiosk.api.order.command.domain.event

import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItem
import com.liveforpresent.cookiosk.shared.core.domain.vo.DomainEvent

data class OrderFinishedAsSuccessEvent(
    val saleItems: MutableSet<OrderItem>,
    val totalPrice: Int,
    val kioskId: Long
): DomainEvent
