package com.liveforpresent.cookiosk.api.cart.command.application.handler

import com.liveforpresent.cookiosk.api.cart.command.application.command.AddCartItemCommand
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRedisRepository
import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItem
import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItemProps
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartItemId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import com.liveforpresent.cookiosk.shared.core.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddItemHandler(
    // private val cartCommandRepository: CartCommandRepository,
    private val cartCommandRedisRepository: CartCommandRedisRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: AddCartItemCommand) {
        val cart = cartCommandRedisRepository.findById(command.cartId)

        val cartItem = CartItem.create(
            CartItemId(SnowflakeIdUtil.generateId()), CartItemProps(
                name = command.name,
                price = Money.create(command.price),
                imageUrl = ImageUrl.create(command.imageUrl),
                quantity = command.quantity,
                productId = ProductId(command.productId)
            )
        )

        val updatedCart = cart.addItem(cartItem)

        eventPublisher.publish(updatedCart)

        cartCommandRedisRepository.save(updatedCart)
    }
}
