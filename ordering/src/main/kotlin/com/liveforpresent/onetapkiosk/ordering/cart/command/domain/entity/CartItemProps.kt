package com.liveforpresent.onetapkiosk.ordering.cart.command.domain.entity

import com.liveforpresent.onetapkiosk.common.core.domain.vo.ImageUrl
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId

data class CartItemProps (
    val name: String,
    val price: Money,
    val imageUrl: ImageUrl,
    val quantity: Int,
    val productId: ProductId
)
