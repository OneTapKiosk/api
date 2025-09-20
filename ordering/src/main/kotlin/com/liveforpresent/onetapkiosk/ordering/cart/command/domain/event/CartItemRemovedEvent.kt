package com.liveforpresent.onetapkiosk.ordering.cart.command.domain.event

import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent

data class CartItemRemovedEvent(
    val productId: Long
): DomainEvent
