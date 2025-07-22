package com.liveforpresent.cookiosk.api.inventory.command.application.listener

import com.liveforpresent.cookiosk.api.cart.command.domain.event.CartItemRemovedEvent
import com.liveforpresent.cookiosk.api.inventory.command.application.command.IncreaseInventoryQuantityCommand
import com.liveforpresent.cookiosk.api.inventory.command.application.handler.IncreaseInventoryQuantityHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class InventoryQuantityIncreaseListener(
    private val increaseInventoryQuantityHandler: IncreaseInventoryQuantityHandler
) {
    @TransactionalEventListener
    fun handle(event: CartItemRemovedEvent) {
        val command = IncreaseInventoryQuantityCommand(event.productId)
        increaseInventoryQuantityHandler.execute(command)
    }
}
