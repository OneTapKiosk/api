package com.liveforpresent.cookiosk.api.order.command.domain.entity

import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money

data class OrderItemProps (
    val name: String,
    val price: Money,
    val quantity: Int,
    val productId: ProductId
)
