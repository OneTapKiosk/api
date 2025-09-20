package com.liveforpresent.onetapkiosk.ordering.order.command.domain.entity

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId

data class OrderItemProps (
    val name: String,
    val price: Money,
    val quantity: Int,
    val productId: ProductId
)
