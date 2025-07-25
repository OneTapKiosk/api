package com.liveforpresent.cookiosk.api.cart.command.application.handler

import com.liveforpresent.cookiosk.api.cart.command.application.command.RemoveCartItemCommand
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRedisRepository
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartItemId
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
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
