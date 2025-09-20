package com.liveforpresent.onetapkiosk.ordering.cart.query.infrastructure

import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartItemModel
import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartModel
import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartQueryRedisRepository
import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import org.springframework.stereotype.Repository

@Repository
class CartQueryRedisRepositoryImpl(
    private val cartQueryCrudRepository: CartQueryCrudRepository
): CartQueryRedisRepository {
    override fun findById(id: Long): CartModel {
        val cartEntity = cartQueryCrudRepository.findById(id)
            .orElseThrow {
                CustomException(
                    CustomExceptionCode.CART_NOT_FOUND,
                    "[CartQueryRepository] CartId: ${id}에 해당하는 장바구니가 존재하지 않습니다."
                )
            }

        val cartModel = CartModel(
            id = cartEntity.id.toString(),
            cartItems = cartEntity.cartItems.map { CartItemModel(
                id = it.id.toString(),
                name = it.name,
                price = it.price,
                quantity = it.quantity,
                imageUrl = it.imageUrl,
                productId = it.productId.toString()
            ) }.toSet(),
            totalPrice = cartEntity.totalPrice,
            createdAt = cartEntity.createdAt,
            updatedAt = cartEntity.updatedAt,
            kioskId = cartEntity.kioskId
        )

        return cartModel
    }
}
