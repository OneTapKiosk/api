package com.liveforpresent.onetapkiosk.ordering.sale.query.application.query

import java.time.Instant

data class GetSaleSummaryByItemQuery(
    val startAt: Instant?,
    val endAt: Instant?,
    val kioskId: Long
)
