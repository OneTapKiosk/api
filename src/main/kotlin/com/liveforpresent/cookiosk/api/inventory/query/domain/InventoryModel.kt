package com.liveforpresent.cookiosk.api.inventory.query.domain

import java.time.Instant

data class InventoryModel(
    val id: Long,
    val isAvailable: Boolean,
    val quantity: Int,
    val productId: Long,
    val description: String,
    val categoryId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
)
