package com.liveforpresent.cookiosk.api.cart.command.infrastructure.entity

import com.liveforpresent.cookiosk.api.cart.command.domain.Cart
import com.liveforpresent.cookiosk.api.cart.command.domain.CartProps
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "cart")
class CartEntity(
    @Id
    @Column(nullable = false)
    val id: Long,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    val cartItems: MutableSet<CartItemEntity>,

    @Column(nullable = false)
    val totalPrice: Int,

    @Column(nullable = false)
    val createdAt: Instant,

    @Column(nullable = false)
    val updatedAt: Instant,

    @Column(nullable = false)
    val kioskId: Long,
) {
    companion object {
        fun toPersistence(cart: Cart): CartEntity {
            return CartEntity(
                id = cart.id.value,
                cartItems = cart.cartItems.map { cartItem -> CartItemEntity.toPersistence(cartItem) }.toMutableSet(),
                totalPrice = cart.totalPrice.value,
                createdAt = cart.createdAt,
                updatedAt = cart.updatedAt,
                kioskId = cart.kioskId.value,
            )
        }

        fun toDomain(cartEntity: CartEntity): Cart {
            val props = CartProps(
                cartItems = cartEntity.cartItems.map { cartItemEntity -> CartItemEntity.toDomain(cartItemEntity) }.toMutableSet(),
                totalPrice = Money.create(cartEntity.totalPrice),
                createdAt = cartEntity.createdAt,
                updatedAt = cartEntity.updatedAt,
                kioskId = KioskId(cartEntity.kioskId)
            )

            return Cart.create(id = CartId(cartEntity.id), props = props)
        }
    }
}
