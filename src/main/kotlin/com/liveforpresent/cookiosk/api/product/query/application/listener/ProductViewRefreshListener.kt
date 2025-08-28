package com.liveforpresent.cookiosk.api.product.query.application.listener

import com.liveforpresent.cookiosk.api.product.command.domain.event.ProductCreatedEvent
import com.liveforpresent.cookiosk.api.product.command.domain.event.ProductDeletedEvent
import com.liveforpresent.cookiosk.api.product.command.domain.event.ProductUpdatedEvent
import com.liveforpresent.cookiosk.api.product.query.application.handler.RefreshProductViewHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ProductViewRefreshListener(
    private val refreshProductViewHandler: RefreshProductViewHandler
) {
    @TransactionalEventListener
    fun handleCreate(event: ProductCreatedEvent) {
        refreshProductViewHandler.execute()
    }

    @TransactionalEventListener
    fun handleDelete(event: ProductDeletedEvent) {
        refreshProductViewHandler.execute()
    }

    @TransactionalEventListener
    fun handleUpdate(event: ProductUpdatedEvent) {
        refreshProductViewHandler.execute()
    }
}
