package com.liveforpresent.cookiosk.api.cart.command.infrastructure

import com.liveforpresent.cookiosk.api.cart.command.domain.Cart
import com.liveforpresent.cookiosk.api.cart.command.domain.CartProps
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.time.Duration
import java.time.Instant

@RedisHash("cart")
data class CartRedisEntity(
    @Id
    val id: Long,
    val cartItems: MutableSet<CartItemEntity> = mutableSetOf(),
    val totalPrice: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val kioskId: Long
) {
    @TimeToLive
    val ttl: Long = Duration.ofMinutes(3).seconds

    companion object {
        fun toPersistence(cart: Cart): CartRedisEntity {
            return CartRedisEntity(
                id = cart.id.value,
                cartItems = cart.cartItems.map { CartItemEntity.toPersistence(it) }.toMutableSet(),
                totalPrice = cart.totalPrice.value,
                createdAt = cart.createdAt,
                updatedAt = cart.updatedAt,
                kioskId = cart.kioskId.value
            )
        }

        fun toDomain(cartRedisEntity: CartRedisEntity): Cart {
            val cartProps = CartProps(
                cartItems = cartRedisEntity.cartItems.map { cartItemEntity -> CartItemEntity.toDomain(cartItemEntity) }.toMutableSet(),
                totalPrice = Money.create(cartRedisEntity.totalPrice),
                createdAt = cartRedisEntity.createdAt,
                updatedAt = cartRedisEntity.updatedAt,
                kioskId = KioskId(cartRedisEntity.kioskId)
            )

            return Cart.create(id = CartId(cartRedisEntity.id), cartProps)
        }
    }
}
