package com.liveforpresent.cookiosk.api.inventory.command.application.command

data class IncreaseInventoryQuantityCommand(
    val productId: Long,
    val amount: Int
)
