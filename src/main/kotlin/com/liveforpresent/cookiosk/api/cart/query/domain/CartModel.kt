package com.liveforpresent.cookiosk.api.cart.query.domain

import java.time.Instant

data class CartModel (
    val id: Long,
    val cartItems: Set<CartItemModel>,
    val totalPrice: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val kioskId: Long
)
