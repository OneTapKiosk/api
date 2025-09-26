package com.liveforpresent.onetapkiosk.ordering.cart.command.application.handler

import com.liveforpresent.onetapkiosk.ordering.cart.command.application.command.RemoveCartItemCommand
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.CartCommandRedisRepository
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.vo.CartItemId
import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RemoveItemHandler(
    //val cartCommandRepository: CartCommandRepository,
    val cartCommandRedisRepository: CartCommandRedisRepository,
    val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: RemoveCartItemCommand) {
        val cart = cartCommandRedisRepository.findById(command.cartId)

        val updatedCart = cart.removeItem(CartItemId(command.cartItemId))

        cartCommandRedisRepository.save(updatedCart)

        eventPublisher.publish(updatedCart)
    }
}
