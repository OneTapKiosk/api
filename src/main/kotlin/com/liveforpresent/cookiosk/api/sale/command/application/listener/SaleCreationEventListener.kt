package com.liveforpresent.cookiosk.api.sale.command.application.listener

import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderFinishedAsSuccessEvent
import com.liveforpresent.cookiosk.api.sale.command.application.command.CreateSaleCommand
import com.liveforpresent.cookiosk.api.sale.command.application.command.SaleItemCommand
import com.liveforpresent.cookiosk.api.sale.command.application.handler.CreateSaleHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SaleCreationEventListener(
    private val createSaleHandler: CreateSaleHandler
) {
    @TransactionalEventListener
    fun handle(event: OrderFinishedAsSuccessEvent) {
        val saleItems = event.saleItems.map { SaleItemCommand(it.name, it.price.value, it.quantity) }.toMutableList()
        val command = CreateSaleCommand(
            totalPrice = event.totalPrice,
            saleItems = saleItems,
            kioskId = event.kioskId
        )

        createSaleHandler.execute(command)
    }
}
