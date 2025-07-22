package com.liveforpresent.cookiosk.api.cart.command.domain.event

import com.liveforpresent.cookiosk.shared.core.domain.vo.DomainEvent

data class CartItemAddedEvent(
    val productId: Long
): DomainEvent
