package com.liveforpresent.cookiosk.api.cart.command.domain

import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItem
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import java.time.Instant

data class CartProps(
    val cartItems: MutableSet<CartItem>,
    val totalPrice: Money,
    val createdAt: Instant,
    val updatedAt: Instant,
    val kioskId: KioskId
)
