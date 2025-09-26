package com.liveforpresent.onetapkiosk.ordering.sale.query.domain

import java.time.Instant

data class SaleModel(
    val createdAt: Instant,
    val totalPrice: Int,
    val kioskId: String
)
