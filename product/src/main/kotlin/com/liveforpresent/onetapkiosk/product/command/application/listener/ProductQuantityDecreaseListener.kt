package com.liveforpresent.onetapkiosk.product.command.application.listener

import com.liveforpresent.onetapkiosk.product.command.application.handler.DecreaseProductQuantityHandler
import org.springframework.stereotype.Component

@Component
class ProductQuantityDecreaseListener(
    private val decreaseProductQuantityHandler: DecreaseProductQuantityHandler
) {
}
