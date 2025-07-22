package com.liveforpresent.cookiosk.api.cart.command.application.command

data class CartItemCommand(
    val cartId: Long,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val quantity: Int,
    val productId: Long
)
