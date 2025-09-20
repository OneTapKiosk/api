package com.liveforpresent.onetapkiosk.ordering.sale.command.domain

interface SaleCommandRepository {
    fun save(sale: Sale): Sale
}
