package com.liveforpresent.cookiosk.api.product.query.application.listener

import com.liveforpresent.cookiosk.api.product.command.domain.event.ProductCreatedEvent
import com.liveforpresent.cookiosk.api.product.command.domain.event.ProductDeletedEvent
import com.liveforpresent.cookiosk.api.product.command.domain.event.ProductUpdatedEvent
import com.liveforpresent.cookiosk.api.product.query.application.handler.RefreshProductViewHandler
import com.liveforpresent.cookiosk.shared.core.domain.vo.DomainEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RefreshProductViewListener(
    private val refreshProductViewHandler: RefreshProductViewHandler
) {
    @TransactionalEventListener(
        classes = [
            ProductCreatedEvent::class,
            ProductUpdatedEvent::class,
            ProductDeletedEvent::class
        ]
    )
    fun handle(event: DomainEvent) {
        refreshProductViewHandler.execute()
    }
}
