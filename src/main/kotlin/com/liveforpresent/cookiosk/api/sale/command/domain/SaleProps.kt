package com.liveforpresent.cookiosk.api.sale.command.domain

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.sale.command.domain.entity.SaleItem
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import java.time.Instant

data class SaleProps (
    val saleItems: MutableList<SaleItem>,
    val createdAt: Instant,
    val totalPrice: Money,
    val kioskId: KioskId,
)
