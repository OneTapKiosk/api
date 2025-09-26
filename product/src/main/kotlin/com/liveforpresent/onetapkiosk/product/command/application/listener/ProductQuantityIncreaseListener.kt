package com.liveforpresent.onetapkiosk.product.command.application.listener

import com.liveforpresent.onetapkiosk.product.command.application.handler.IncreaseProductQuantityHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ProductQuantityIncreaseListener(
    private val increaseProductQuantityHandler: IncreaseProductQuantityHandler
) {
}
