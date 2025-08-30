package com.liveforpresent.cookiosk.api.sale.query.domain

import java.time.Instant

interface SaleByItemQueryRepository {
    fun findByItem(
        startAt: Instant?,
        endAt: Instant?,
        kioskId: Long
    ): List<SaleByItemModel>
    fun refreshView()
}
