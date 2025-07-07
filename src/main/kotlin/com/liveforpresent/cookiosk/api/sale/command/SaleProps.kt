package com.liveforpresent.cookiosk.api.sale.command

import com.liveforpresent.cookiosk.api.sale.command.entity.SaleItem
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import java.time.Instant

data class SaleProps (
    val saleItems: MutableList<SaleItem>,
    val createdAt: Instant,
    val totalPrice: Money,
)