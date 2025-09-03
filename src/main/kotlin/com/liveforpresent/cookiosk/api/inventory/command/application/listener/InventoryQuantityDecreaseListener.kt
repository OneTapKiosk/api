package com.liveforpresent.cookiosk.api.inventory.command.application.listener

import com.liveforpresent.cookiosk.api.inventory.command.application.command.DecreaseInventoryQuantityCommand
import com.liveforpresent.cookiosk.api.inventory.command.application.handler.DecreaseInventoryQuantityHandler
import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderFinishedAsSuccessEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class InventoryQuantityDecreaseListener(
    private val decreaseInventoryQuantityHandler: DecreaseInventoryQuantityHandler
) {
    @TransactionalEventListener
    fun handle(event: OrderFinishedAsSuccessEvent) {
        event.saleItems.map {
            val command = DecreaseInventoryQuantityCommand(
                productId = it.productId.value,
                amount = it.quantity
            )
            decreaseInventoryQuantityHandler.execute(command)
        }
    }
}
