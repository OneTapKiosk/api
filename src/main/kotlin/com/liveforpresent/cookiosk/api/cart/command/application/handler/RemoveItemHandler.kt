package com.liveforpresent.cookiosk.api.cart.command.application.handler

import com.liveforpresent.cookiosk.api.cart.command.application.command.CartItemCommand
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRepository
import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItem
import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItemProps
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartItemId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RemoveItemHandler(
    val cartCommandRepository: CartCommandRepository
) {
    @Transactional
    fun execute(command: CartItemCommand) {
        val cart = cartCommandRepository.findById(command.productId)

        val cartItem = CartItem.create(
            CartItemId(command.cartItemId), CartItemProps(
                name = command.name,
                price = Money.create(command.price),
                imageUrl = ImageUrl.create(command.imageUrl),
                quantity = command.quantity,
                productId = ProductId(command.productId)
            )
        )

        val updatedCart = cart.removeItem(cartItem)

        cartCommandRepository.save(updatedCart)
    }
}
