package com.liveforpresent.cookiosk.api.inventory.command.domain

import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import java.time.Instant

data class InventoryProps (
    val isAvailable: Boolean,
    val quantity: Int,
    val productId: ProductId,
    val createdAt: Instant,
    val updatedAt: Instant
)
