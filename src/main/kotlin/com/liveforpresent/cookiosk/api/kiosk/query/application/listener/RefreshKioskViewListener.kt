package com.liveforpresent.cookiosk.api.kiosk.query.application.listener

import com.liveforpresent.cookiosk.api.kiosk.command.domain.event.KioskCreatedEvent
import com.liveforpresent.cookiosk.api.kiosk.command.domain.event.KioskDeletedEvent
import com.liveforpresent.cookiosk.api.kiosk.command.domain.event.KioskUpdatedEvent
import com.liveforpresent.cookiosk.api.kiosk.query.application.handler.RefreshKioskViewHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RefreshKioskViewListener(
    private val refreshKioskViewHandler: RefreshKioskViewHandler
) {
    @TransactionalEventListener
    fun handleCreate(event: KioskCreatedEvent) {
        refreshKioskViewHandler.execute()
    }

    @TransactionalEventListener
    fun handleUpdate(event: KioskUpdatedEvent) {
        refreshKioskViewHandler.execute()
    }

    @TransactionalEventListener
    fun handleDelete(event: KioskDeletedEvent) {
        refreshKioskViewHandler.execute()
    }
}
