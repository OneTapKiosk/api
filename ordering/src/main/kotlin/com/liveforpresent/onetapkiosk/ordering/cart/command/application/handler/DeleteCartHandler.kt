package com.liveforpresent.onetapkiosk.ordering.cart.command.application.handler

import com.liveforpresent.onetapkiosk.ordering.cart.command.application.command.DeleteCartCommand
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.CartCommandRedisRepository
import org.springframework.stereotype.Service

@Service
class DeleteCartHandler(
    val cartCommandRedisRepository: CartCommandRedisRepository,
) {
    fun execute(command: DeleteCartCommand) {
        cartCommandRedisRepository.deleteById(command.cartId)
    }
}
