package com.liveforpresent.cookiosk.api.inventory.query.application.listener

import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryCreatedEvent
import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryQuantityIncreasedEvent
import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryUpdatedEvent
import com.liveforpresent.cookiosk.api.inventory.query.application.handler.RefreshInventoryViewHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class InventoryViewRefreshListener(
    private val refreshInventoryViewHandler: RefreshInventoryViewHandler
) {
    @TransactionalEventListener
    fun handleCreate(event: InventoryCreatedEvent) {
        refreshInventoryViewHandler.execute()
    }

    @TransactionalEventListener
    fun handleIncreaseQuantity(event: InventoryQuantityIncreasedEvent) {
        refreshInventoryViewHandler.execute()
    }

    @TransactionalEventListener
    fun handleUpdate(event: InventoryUpdatedEvent) {
        refreshInventoryViewHandler.execute()
    }
}
