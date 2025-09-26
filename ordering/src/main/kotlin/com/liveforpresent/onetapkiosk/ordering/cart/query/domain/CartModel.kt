package com.liveforpresent.onetapkiosk.ordering.cart.query.domain

import java.time.Instant

data class CartModel (
    val id: String,
    val cartItems: Set<CartItemModel>,
    val totalPrice: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val kioskId: Long
)
