package com.liveforpresent.cookiosk.api.inventory.query.application.listener

import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryCreatedEvent
import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryDeletedEvent
import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryQuantityIncreasedEvent
import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryUpdatedEvent
import com.liveforpresent.cookiosk.api.inventory.query.application.handler.RefreshInventoryViewHandler
import com.liveforpresent.cookiosk.shared.core.domain.vo.DomainEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RefreshInventoryViewListener(
    private val refreshInventoryViewHandler: RefreshInventoryViewHandler
) {
    @TransactionalEventListener(
        classes = [
            InventoryCreatedEvent::class,
            InventoryUpdatedEvent::class,
            InventoryDeletedEvent::class,
            InventoryQuantityIncreasedEvent::class,
        ]
    )
    fun handle(event: DomainEvent) {
        refreshInventoryViewHandler.execute()
    }
}
