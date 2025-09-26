package com.liveforpresent.onetapkiosk.ordering.cart.command.infrastructure

import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.Cart
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.CartCommandRepository
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

    override fun findById(id: Long): Cart {
        val cartEntity = cartCommandJpaRepository.findById(id)
            .orElseThrow { IllegalArgumentException("해당 장바구니는 존재하지 않습니다.") }

        return CartEntity.toDomain(cartEntity)
    }
}
