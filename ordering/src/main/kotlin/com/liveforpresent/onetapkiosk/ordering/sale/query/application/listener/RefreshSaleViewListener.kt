package com.liveforpresent.onetapkiosk.ordering.sale.query.application.listener

import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.event.SaleCreatedEvent
import com.liveforpresent.onetapkiosk.ordering.sale.query.application.handler.RefreshSaleByItemViewHandler
import com.liveforpresent.onetapkiosk.ordering.sale.query.application.handler.RefreshSaleViewHandler
import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RefreshSaleViewListener(
    private val refreshSaleViewHandler: RefreshSaleViewHandler,
    private val refreshSaleByItemViewHandler: RefreshSaleByItemViewHandler
) {
    @TransactionalEventListener(
        classes = [
            SaleCreatedEvent::class,
        ]
    )
    fun handle(event: DomainEvent) {
        refreshSaleViewHandler.execute()
        refreshSaleByItemViewHandler.execute()
    }
}
