package com.liveforpresent.cookiosk.api.cart.command.infrastructure

import com.liveforpresent.cookiosk.api.cart.command.domain.Cart
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRedisRepository
import org.springframework.stereotype.Repository

@Repository
class CartCommandRedisRepositoryImpl(
    private val cartCommandCrudRepository: CartCommandCrudRepository
): CartCommandRedisRepository {
    override fun save(cart: Cart) {
        val cartRedisEntity = CartRedisEntity.toPersistence(cart)
        cartCommandCrudRepository.save(cartRedisEntity)
    }

    override fun deleteById(cartId: Long) {
        cartCommandCrudRepository.deleteById(cartId)
    }

    override fun findById(cartId: Long): Cart {
        val cartRedisEntity = cartCommandCrudRepository.findById(cartId)
            .orElseThrow { IllegalArgumentException("해당 장바구니가 존재하지 않습니다.") }

        return CartRedisEntity.toDomain(cartRedisEntity)
    }
}
