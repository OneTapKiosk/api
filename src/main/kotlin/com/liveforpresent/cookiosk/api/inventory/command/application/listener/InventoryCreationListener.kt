package com.liveforpresent.cookiosk.api.inventory.command.application.listener

import com.liveforpresent.cookiosk.api.inventory.command.application.command.CreateInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.application.handler.CreateInventoryHandler
import com.liveforpresent.cookiosk.api.product.command.domain.event.ProductCreatedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class InventoryCreationListener(
    private val createInventoryHandler: CreateInventoryHandler
) {
    @TransactionalEventListener
    fun handle(event: ProductCreatedEvent) {
        val command = CreateInventoryCommand(
            productId = event.productId,
            isAvailable = true,
            quantity = event.quantity,
            kioskId = event.kioskId,
        )

        createInventoryHandler.execute(command)
    }
}
