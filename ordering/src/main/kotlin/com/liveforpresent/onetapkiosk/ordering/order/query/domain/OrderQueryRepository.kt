package com.liveforpresent.onetapkiosk.ordering.order.query.domain

import java.time.Instant

interface OrderQueryRepository {
    fun findByCriteria(
        startAt: Instant?,
        endAt: Instant?,
        statuses: List<String>?,
        sortBy: String?
    ): List<OrderModel>
    fun findById(orderId: Long): OrderDetailModel
    fun refreshView()
}
