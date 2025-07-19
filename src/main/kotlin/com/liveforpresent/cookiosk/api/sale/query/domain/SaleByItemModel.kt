package com.liveforpresent.cookiosk.api.sale.query.domain

interface SaleByItemModel {
    fun getName(): String
    fun getTotalPrice(): Long
    fun getTotalQuantity(): Long
}
