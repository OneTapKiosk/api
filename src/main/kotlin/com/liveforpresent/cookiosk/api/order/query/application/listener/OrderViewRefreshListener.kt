package com.liveforpresent.cookiosk.api.order.query.application.listener

import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderCreatedEvent
import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderFinishedAsRejectEvent
import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderFinishedAsSuccessEvent
import com.liveforpresent.cookiosk.api.order.command.domain.event.OrderProcessedToPaymentEvent
import com.liveforpresent.cookiosk.api.order.query.application.handler.RefreshOrderViewHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class OrderViewRefreshListener(
    private val refreshOrderViewHandler: RefreshOrderViewHandler,
) {
    @TransactionalEventListener
    fun handleCreate(event: OrderCreatedEvent) {
        refreshOrderViewHandler.execute()
    }

    @TransactionalEventListener
    fun handleProcessToPayment(event: OrderProcessedToPaymentEvent) {
        refreshOrderViewHandler.execute()
    }

    @TransactionalEventListener
    fun handleFinishAsSuccess(event: OrderFinishedAsSuccessEvent) {
        refreshOrderViewHandler.execute()
    }

    @TransactionalEventListener
    fun handleFinishAsReject(event: OrderFinishedAsRejectEvent) {
        refreshOrderViewHandler.execute()
    }
}
