package com.liveforpresent.cookiosk.api.sale.query.domain

data class SaleItemModel(
    val saleItemId: String,
    val name: String,
    val quantity: Int,
    val price: Int
)
