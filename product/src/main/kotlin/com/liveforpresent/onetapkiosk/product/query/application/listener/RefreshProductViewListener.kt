package com.liveforpresent.onetapkiosk.product.query.application.listener

import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent
import com.liveforpresent.onetapkiosk.product.command.domain.event.*
import com.liveforpresent.onetapkiosk.product.query.application.handler.RefreshProductViewHandler
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
            ProductDeletedEvent::class,
            ProductQuantityIncreasedEvent::class,
            ProductQuantityDecreasedEvent::class
        ]
    )
    fun handle(event: DomainEvent) {
        refreshProductViewHandler.execute()
    }
}
