package com.liveforpresent.onetapkiosk.ordering.cart.command.application.command

data class AddCartItemCommand(
    val cartId: Long,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val quantity: Int,
    val productId: Long
)
