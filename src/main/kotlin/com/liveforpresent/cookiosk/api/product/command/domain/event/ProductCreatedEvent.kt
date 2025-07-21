package com.liveforpresent.cookiosk.api.product.command.domain.event

import com.liveforpresent.cookiosk.shared.core.domain.vo.DomainEvent

data class ProductCreatedEvent(
    val productId: Long,
    val quantity: Int,
    val kioskId: Long
): DomainEvent
