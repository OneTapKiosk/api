package com.liveforpresent.cookiosk.api.sale.query.application.listener

import com.liveforpresent.cookiosk.api.sale.command.domain.event.SaleCreatedEvent
import com.liveforpresent.cookiosk.api.sale.query.application.handler.RefreshSaleByItemViewHandler
import com.liveforpresent.cookiosk.api.sale.query.application.handler.RefreshSaleViewHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RefreshSaleViewListener(
    private val refreshSaleViewHandler: RefreshSaleViewHandler,
    private val refreshSaleByItemViewHandler: RefreshSaleByItemViewHandler
) {
    @TransactionalEventListener
    fun handleCreate(event: SaleCreatedEvent) {
        refreshSaleViewHandler.execute()
        refreshSaleByItemViewHandler.execute()
    }
}
