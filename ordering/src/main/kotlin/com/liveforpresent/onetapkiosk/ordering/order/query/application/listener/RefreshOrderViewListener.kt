package com.liveforpresent.onetapkiosk.ordering.order.query.application.listener

import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent
import com.liveforpresent.onetapkiosk.ordering.order.query.application.handler.RefreshOrderViewHandler
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.event.*
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
