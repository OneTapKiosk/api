package com.liveforpresent.onetapkiosk.ordering.sale.command.domain.entity

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money

data class SaleItemProps(
    val name: String,
    val quantity: Int,
    val price: Money,
)
