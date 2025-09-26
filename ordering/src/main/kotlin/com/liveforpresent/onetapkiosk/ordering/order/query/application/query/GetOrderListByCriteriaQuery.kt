package com.liveforpresent.onetapkiosk.ordering.order.query.application.query

import java.time.Instant

data class GetOrderListByCriteriaQuery(
    val startAt: Instant?,
    val endAt: Instant?,
    val statuses: List<String>?,
    val sortBy: String?,
)
