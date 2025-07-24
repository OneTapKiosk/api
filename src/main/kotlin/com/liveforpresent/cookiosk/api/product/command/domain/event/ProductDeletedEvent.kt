package com.liveforpresent.cookiosk.api.product.command.domain.event

import com.liveforpresent.cookiosk.shared.core.domain.vo.DomainEvent

data class ProductDeletedEvent(
    val productId: Long
): DomainEvent
