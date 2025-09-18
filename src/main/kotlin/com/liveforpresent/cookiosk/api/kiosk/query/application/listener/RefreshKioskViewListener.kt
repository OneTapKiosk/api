package com.liveforpresent.cookiosk.api.kiosk.query.application.listener

import com.liveforpresent.cookiosk.api.kiosk.command.domain.event.KioskCreatedEvent
import com.liveforpresent.cookiosk.api.kiosk.command.domain.event.KioskDeletedEvent
import com.liveforpresent.cookiosk.api.kiosk.command.domain.event.KioskUpdatedEvent
import com.liveforpresent.cookiosk.api.kiosk.query.application.handler.RefreshKioskViewHandler
import com.liveforpresent.cookiosk.shared.core.domain.vo.DomainEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RefreshKioskViewListener(
    private val refreshKioskViewHandler: RefreshKioskViewHandler
) {
    @TransactionalEventListener(
        classes = [
            KioskCreatedEvent::class,
            KioskUpdatedEvent::class,
            KioskDeletedEvent::class,
        ]
    )
    fun handle(event: DomainEvent) {
        refreshKioskViewHandler.execute()
    }
}
