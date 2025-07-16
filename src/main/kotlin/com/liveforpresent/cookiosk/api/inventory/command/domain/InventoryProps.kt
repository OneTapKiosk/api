package com.liveforpresent.cookiosk.api.inventory.command.domain

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import java.time.Instant

data class InventoryProps (
    val isAvailable: Boolean,
    val quantity: Int,
    val productId: ProductId,
    val createdAt: Instant,
    val updatedAt: Instant,
    val kioskId: KioskId,
    val isDeleted: Boolean = false,
    val deletedAt: Instant? = null,
)
