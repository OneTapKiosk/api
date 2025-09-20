package com.liveforpresent.onetapkiosk.ordering.cart.command.infrastructure

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.Cart
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.CartCommandRedisRepository
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

    override fun findById(id: Long): Cart {
        val cartRedisEntity = cartCommandCrudRepository.findById(id)
            .orElseThrow {
                CustomException(
                    CustomExceptionCode.CART_NOT_FOUND,
                    "[CartCommandRepository] CartId: ${id}에 해당하는 장바구니가 존재하지 않습니다."
                )
            }

        return CartRedisEntity.toDomain(cartRedisEntity)
    }
}
