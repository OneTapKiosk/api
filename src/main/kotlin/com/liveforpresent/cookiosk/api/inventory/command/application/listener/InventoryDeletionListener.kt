package com.liveforpresent.cookiosk.api.inventory.command.application.listener

import com.liveforpresent.cookiosk.api.inventory.command.application.command.DeleteInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.application.handler.DeleteInventoryHandler
import com.liveforpresent.cookiosk.api.product.command.domain.event.ProductDeletedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class InventoryDeletionListener(
    private val deleteInventoryHandler: DeleteInventoryHandler
) {
    @TransactionalEventListener
    fun handle(event: ProductDeletedEvent) {
        val command = DeleteInventoryCommand(event.productId)
        deleteInventoryHandler.execute(command)
    }
}
