package com.liveforpresent.cookiosk.api.cart.command.presentation.dto.request

data class AddCartItemReqDto(
    val name: String,
    val price: Int,
    val imageUrl: String,
    val quantity: Int,
    val productId: Long
)
