package com.liveforpresent.onetapkiosk.ordering.sale.command.application.command

data class CreateSaleCommand(
    val saleItems: MutableList<SaleItemCommand>,
    val totalPrice: Int,
    val kioskId: Long
)
