package com.liveforpresent.cookiosk.api.sale.command.application.command

data class SaleItemCommand(
    val name: String,
    val price: Int,
    val quantity: Int
)
