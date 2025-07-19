package com.liveforpresent.cookiosk.api.cart.query.infrastructure

import com.liveforpresent.cookiosk.api.cart.query.domain.CartItemModel
import com.liveforpresent.cookiosk.api.cart.query.domain.CartModel
import com.liveforpresent.cookiosk.api.cart.query.domain.CartQueryRepository
import org.springframework.stereotype.Repository

@Repository
class CartQueryRepositoryImpl(
    private val cartQueryJpaRepository: CartQueryJpaRepository
): CartQueryRepository {
    override fun findById(id: Long): CartModel {
        val cartEntity = cartQueryJpaRepository.findById(id)
            .orElseThrow { IllegalArgumentException("해당 Cart는 존재하지 않습니다") }

        val cartModel = CartModel(
            id = cartEntity.id,
            cartItems = cartEntity.cartItems.map {
                CartItemModel(
                    id = it.id,
                    name = it.name,
                    quantity = it.quantity,
                    price = it.price,
                    imageUrl = it.imageUrl,
                )
            }.toSet(),
            totalPrice = cartEntity.totalPrice,
            createdAt = cartEntity.createdAt,
            updatedAt = cartEntity.updatedAt,
            kioskId = cartEntity.kioskId,
        )

        return cartModel
    }

}
