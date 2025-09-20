package com.liveforpresent.onetapkiosk.ordering.cart.command.application.command

data class RemoveCartItemCommand(
    val cartId: Long,
    val cartItemId: Long
)
