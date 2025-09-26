package com.liveforpresent.onetapkiosk.ordering.sale.query.domain

import java.time.Instant

interface SaleQueryRepository {
    fun findByCriteria(
        startAt: Instant?,
        endAt: Instant?,
        sortBy: String?,
        kioskId: Long
    ): List<SaleModel>
    fun refreshView()
}
