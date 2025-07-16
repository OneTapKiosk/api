package com.liveforpresent.cookiosk.api.inventory.command.presentation.dto.request

data class UpdateInventoryReqDto (
    val isAvailable: Boolean?,
    val quantity: Int?,
    val productId: Long?,
)
