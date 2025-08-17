package com.liveforpresent.cookiosk.api.cart.query.infrastructure

import com.liveforpresent.cookiosk.api.cart.query.domain.CartItemModel
import com.liveforpresent.cookiosk.api.cart.query.domain.CartModel
import com.liveforpresent.cookiosk.api.cart.query.domain.CartQueryRedisRepository
import org.springframework.stereotype.Repository

@Repository
class CartQueryRedisRepositoryImpl(
    private val cartQueryCrudRepository: CartQueryCrudRepository
): CartQueryRedisRepository {
    override fun findById(id: Long): CartModel {
        val cartEntity = cartQueryCrudRepository.findById(id)
            .orElseThrow { IllegalArgumentException("해당 장바구니가 존재하지 않습니다.") }

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
