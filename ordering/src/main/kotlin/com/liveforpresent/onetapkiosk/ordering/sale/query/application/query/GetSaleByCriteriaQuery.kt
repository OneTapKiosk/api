package com.liveforpresent.onetapkiosk.ordering.sale.query.application.query

import java.time.Instant

data class GetSaleByCriteriaQuery(
    val startAt: Instant?,
    val endAt: Instant?,
    val sortBy: String?,
    val kioskId: Long
)
