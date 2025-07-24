package com.liveforpresent.cookiosk.api.inventory.command.application.command

data class CreateInventoryCommand(
    val isAvailable: Boolean,
    val quantity: Int,
    val productId: Long,
    val kioskId: Long,
)
