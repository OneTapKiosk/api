package com.liveforpresent.cookiosk.api.inventory.query.domain

import java.time.Instant

data class InventoryModel(
    val id: String,
    val isAvailable: Boolean,
    val quantity: Int,
    val productId: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)
