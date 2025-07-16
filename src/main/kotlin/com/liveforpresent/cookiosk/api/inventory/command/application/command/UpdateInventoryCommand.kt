package com.liveforpresent.cookiosk.api.inventory.command.application.command

data class UpdateInventoryCommand(
    val inventoryId: Long,
    val isAvailable: Boolean?,
    val quantity: Int?,
    val productId: Long?,
)
