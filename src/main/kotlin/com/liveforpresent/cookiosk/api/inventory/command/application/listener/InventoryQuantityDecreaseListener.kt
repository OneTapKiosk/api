package com.liveforpresent.cookiosk.api.inventory.command.application.listener

import com.liveforpresent.cookiosk.api.cart.command.domain.event.CartItemAddedEvent
import com.liveforpresent.cookiosk.api.inventory.command.application.command.DecreaseInventoryQuantityCommand
import com.liveforpresent.cookiosk.api.inventory.command.application.handler.DecreaseInventoryQuantityHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class InventoryQuantityDecreaseListener(
    private val decreaseInventoryQuantityHandler: DecreaseInventoryQuantityHandler
) {
    @TransactionalEventListener
    fun handle(event: CartItemAddedEvent) {
        val command = DecreaseInventoryQuantityCommand(event.productId)
        decreaseInventoryQuantityHandler.execute(command)
    }
}
