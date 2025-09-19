package com.liveforpresent.cookiosk.api.order.query.application.listener

import com.liveforpresent.cookiosk.api.order.command.domain.event.*
import com.liveforpresent.cookiosk.api.order.query.application.handler.RefreshOrderViewHandler
import com.liveforpresent.cookiosk.shared.core.domain.vo.DomainEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RefreshOrderViewListener(
    private val refreshOrderViewHandler: RefreshOrderViewHandler,
) {
    @TransactionalEventListener(
        classes = [
            OrderCreatedEvent::class,
            OrderProcessedToPaymentEvent::class,
            OrderFinishedAsSuccessEvent::class,
            OrderFinishedAsRejectEvent::class,
            OrderFinishedAsCancelEvent::class,
        ]
    )
    fun handle(event: DomainEvent) {
        refreshOrderViewHandler.execute()
    }
}
