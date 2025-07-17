package com.liveforpresent.cookiosk.api.sale.command.presentation.dto.request

import com.liveforpresent.cookiosk.api.sale.command.application.command.CreateSaleCommand
import com.liveforpresent.cookiosk.api.sale.command.application.command.SaleItemCommand

data class CreateSaleReqDto(
    val saleItemList: MutableList<SaleItemDto>,
    val totalPrice: Int,
    val kioskId: Long
) {
    fun toCommand(): CreateSaleCommand {
        val saleItems = saleItemList.map { SaleItemCommand(
            name = it.name,
            price = it.price,
            quantity = it.quantity
        ) }.toMutableList()

        return CreateSaleCommand(saleItems, totalPrice, kioskId)
    }
}
