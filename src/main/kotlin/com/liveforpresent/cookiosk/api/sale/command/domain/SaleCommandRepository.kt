package com.liveforpresent.cookiosk.api.sale.command.domain

interface SaleCommandRepository {
    fun save(sale: Sale): Sale
}
