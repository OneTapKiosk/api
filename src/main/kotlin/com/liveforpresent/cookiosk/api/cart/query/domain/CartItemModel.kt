package com.liveforpresent.cookiosk.api.cart.query.domain

data class CartItemModel(
    val id: Long,
    val name: String,
    val price: Int,
    val quantity: Int,
    val imageUrl: String
)
