package com.liveforpresent.cookiosk.api.cart.command.infrastructure

import com.liveforpresent.cookiosk.api.cart.command.domain.Cart
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRepository
import com.liveforpresent.cookiosk.api.cart.command.infrastructure.entity.CartEntity
import org.springframework.stereotype.Repository

@Repository
class CartCommandRepositoryImpl(
    private val cartCommandJpaRepository: CartCommandJpaRepository
): CartCommandRepository {
    override fun save(cart: Cart): Cart {
        val cartEntity = CartEntity.toPersistence(cart)
        cartCommandJpaRepository.save(cartEntity)

        return cart
    }
}
