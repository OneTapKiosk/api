package com.liveforpresent.cookiosk.api.sale.query.domain

data class SaleItemModel(
    val saleItemId: Long,
    val name: String,
    val quantity: Int,
    val price: Int
)
