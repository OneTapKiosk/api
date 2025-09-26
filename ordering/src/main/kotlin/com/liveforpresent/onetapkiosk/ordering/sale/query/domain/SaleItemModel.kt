package com.liveforpresent.onetapkiosk.ordering.sale.query.domain

data class SaleItemModel(
    val saleItemId: String,
    val name: String,
    val quantity: Int,
    val price: Int
)
