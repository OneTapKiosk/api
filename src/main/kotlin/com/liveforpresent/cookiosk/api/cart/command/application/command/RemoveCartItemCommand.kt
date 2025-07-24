package com.liveforpresent.cookiosk.api.cart.command.application.command

data class RemoveCartItemCommand(
    val cartId: Long,
    val cartItemId: Long
)
