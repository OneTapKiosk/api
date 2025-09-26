package com.liveforpresent.onetapkiosk.ordering.cart.command.domain.event

import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent

data class CartItemAddedEvent(
    val productId: Long
): DomainEvent
