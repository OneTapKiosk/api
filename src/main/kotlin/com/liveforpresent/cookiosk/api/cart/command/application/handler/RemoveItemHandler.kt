package com.liveforpresent.cookiosk.api.cart.command.application.handler

import com.liveforpresent.cookiosk.api.cart.command.application.command.RemoveCartItemCommand
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRepository
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartItemId
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RemoveItemHandler(
    val cartCommandRepository: CartCommandRepository,
    val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: RemoveCartItemCommand) {
        val cart = cartCommandRepository.findById(command.cartId)

        val updatedCart = cart.removeItem(CartItemId(command.cartItemId))

        cartCommandRepository.save(updatedCart)

        eventPublisher.publish(updatedCart)
    }
}
