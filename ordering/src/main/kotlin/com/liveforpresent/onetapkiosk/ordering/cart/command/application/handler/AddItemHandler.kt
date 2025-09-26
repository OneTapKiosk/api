package com.liveforpresent.onetapkiosk.ordering.cart.command.application.handler

import com.liveforpresent.onetapkiosk.ordering.cart.command.application.command.AddCartItemCommand
import com.liveforpresent.onetapkiosk.ordering.cart.command.application.dto.AddItemResDto
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.CartCommandRedisRepository
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.entity.CartItem
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.entity.CartItemProps
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.vo.CartItemId
import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.common.core.domain.vo.ImageUrl
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.common.core.infrastructure.util.SnowflakeIdUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddItemHandler(
    // private val cartCommandRepository: CartCommandRepository,
    private val cartCommandRedisRepository: CartCommandRedisRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: AddCartItemCommand): AddItemResDto {
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

        return AddItemResDto(cartItem.id.value.toString())
    }
}
