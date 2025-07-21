package com.liveforpresent.cookiosk.api.product.command.domain.event

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId

data class ProductCreatedEvent(
    val productId: ProductId,
    val quantity: Int,
    val kioskId: KioskId
)
