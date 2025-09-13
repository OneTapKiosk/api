package com.liveforpresent.cookiosk.api.inventory.command.application.listener

import com.liveforpresent.cookiosk.api.inventory.command.application.command.IncreaseInventoryQuantityCommand
import com.liveforpresent.cookiosk.api.inventory.command.application.handler.IncreaseInventoryQuantityHandler
import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderFinishedAsSuccessEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class InventoryQuantityIncreaseListener(
    private val increaseInventoryQuantityHandler: IncreaseInventoryQuantityHandler
) {
    @TransactionalEventListener
    fun handle(event: OrderFinishedAsSuccessEvent) {
        event.saleItems.map {
            val command = IncreaseInventoryQuantityCommand(
                productId = it.productId.value,
                amount = it.quantity
            )
            increaseInventoryQuantityHandler.execute(command)
        }
    }
}
