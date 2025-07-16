package com.liveforpresent.cookiosk.api.inventory.command.application.command

import java.time.Instant

data class CreateInventoryCommand(
    val isAvailable: Boolean,
    val quantity: Int,
    val productId: Long,
    val kioskId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
)
