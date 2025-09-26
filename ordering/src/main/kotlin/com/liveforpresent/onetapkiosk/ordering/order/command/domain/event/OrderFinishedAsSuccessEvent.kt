package com.liveforpresent.onetapkiosk.ordering.order.command.domain.event

import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.entity.OrderItem

data class OrderFinishedAsSuccessEvent(
    val saleItems: MutableSet<OrderItem>,
    val totalPrice: Int,
    val kioskId: Long
): DomainEvent
