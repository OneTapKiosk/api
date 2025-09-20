package com.liveforpresent.onetapkiosk.product.command.domain.event

import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent

data class ProductCreatedEvent(
    val productId: Long,
    val quantity: Int,
    val kioskId: Long
): DomainEvent
