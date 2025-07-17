package com.liveforpresent.cookiosk.api.sale.command.application.command

data class CreateSaleCommand(
    val saleItems: MutableList<SaleItemCommand>,
    val totalPrice: Int,
    val kioskId: Long
)
