package com.liveforpresent.cookiosk.api.cart.command.domain.entity

import com.liveforpresent.cookiosk.api.product.command.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money

data class CartItemProps (
    val name: String,
    val price: Money,
    val imageUrl: ImageUrl,
    val quantity: Int
)
