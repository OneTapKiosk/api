package com.liveforpresent.onetapkiosk.user.kiosk.query.application.listener

import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.event.KioskCreatedEvent
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.event.KioskDeletedEvent
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.event.KioskUpdatedEvent
import com.liveforpresent.onetapkiosk.user.kiosk.query.application.handler.RefreshKioskViewHandler
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
