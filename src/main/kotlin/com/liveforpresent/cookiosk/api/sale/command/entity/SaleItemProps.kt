package com.liveforpresent.cookiosk.api.sale.command.entity

import com.liveforpresent.cookiosk.shared.core.domain.vo.Money

data class SaleItemProps(
    val name: String,
    val quantity: Int,
    val price: Money,
)
