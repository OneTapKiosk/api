package com.liveforpresent.onetapkiosk.ordering.cart.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.entity.CartItem
import java.time.Instant

data class CartProps(
    val cartItems: MutableSet<CartItem>,
    val totalPrice: Money,
    val createdAt: Instant,
    val updatedAt: Instant,
    val kioskId: KioskId
)
