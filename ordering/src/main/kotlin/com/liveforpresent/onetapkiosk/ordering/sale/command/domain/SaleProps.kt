package com.liveforpresent.onetapkiosk.ordering.sale.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.entity.SaleItem
import java.time.Instant

data class SaleProps (
    val saleItems: MutableList<SaleItem>,
    val createdAt: Instant,
    val totalPrice: Money,
    val kioskId: KioskId,
)
