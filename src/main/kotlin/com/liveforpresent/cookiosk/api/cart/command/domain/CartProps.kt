package com.liveforpresent.cookiosk.api.cart.command.domain

import com.liveforpresent.cookiosk.shared.core.domain.vo.Money

data class CartProps(
    val totalPrice: Money,
)
