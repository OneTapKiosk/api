package com.liveforpresent.onetapkiosk.product.command.domain.event

import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent

data class ProductDeletedEvent(
    val productId: Long
): DomainEvent
