package com.liveforpresent.cookiosk.api.cart.query.domain

data class CartItemModel(
    val id: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val imageUrl: String,
    val productId: String,
)
