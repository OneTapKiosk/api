package com.liveforpresent.cookiosk.api.sale.query.domain

import java.time.Instant

interface SaleQueryRepository {
    fun findByCriteria(
        startAt: Instant?,
        endAt: Instant?,
        sortBy: String?,
        kioskId: Long
    ): List<SaleModel>
}
