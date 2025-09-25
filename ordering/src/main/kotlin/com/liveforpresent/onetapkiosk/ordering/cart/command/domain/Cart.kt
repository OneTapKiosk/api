package com.liveforpresent.onetapkiosk.ordering.cart.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.AggregateRoot
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CartId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.entity.CartItem
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.event.CartItemAddedEvent
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.event.CartItemRemovedEvent
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.vo.CartItemId
import com.liveforpresent.onetapkiosk.ordering.shared.exception.CartExceptionCode
import java.time.Instant

class Cart private constructor(
    id: CartId,
    private val props: CartProps
): AggregateRoot<CartId>(id) {
    companion object {
        fun create(id: CartId, props: CartProps): Cart {
            val cart = Cart(id, props)
            cart.validate()

            return cart
        }
    }

    fun validate () {
        require(totalPrice.value >= 0) {
            throw CustomException(
                CartExceptionCode.CART_TOTAL_PRICE_NEGATIVE,
                "[Cart] 총 가격은 음수일 수 없습니다."
            )
        }
    }

    fun addItem(item: CartItem): Cart {
        val existingItem = props.cartItems.find { it.productId == item.productId }

        if (existingItem == null) cartItems.add(item)
        else {
            props.cartItems.remove(existingItem)
            val updatedItem = existingItem.increaseQuantity()
            props.cartItems.add(updatedItem)
        }

        val updatedCart = calculateTotalPrice()

        updatedCart.addDomainEvent(CartItemAddedEvent(item.productId.value))

        return updatedCart
    }

    fun removeItem(cartItemId: CartItemId): Cart {
        val existingItem = props.cartItems.find { it.id == cartItemId }
        require(existingItem != null) {
            throw CustomException(
                CartExceptionCode.CART_ITEM_NOT_FOUND,
                "[CartItem] 장바구니 내에 해당 상품이 존재하지 않습니다."
            )
        }

        if (existingItem.quantity > 1) {
            val updatedItem = existingItem.decreaseQuantity()
            props.cartItems.remove(existingItem)
            props.cartItems.add(updatedItem)
        } else {
            val removed = props.cartItems.remove(existingItem)
            require(removed) {
                throw CustomException(
                    CartExceptionCode.CART_ITEM_REMOVE_FAILURE,
                    "[CartItem] 장바구니에서 상품 제거 실패: ${cartItemId.value}"
                )
            }
        }

        val updatedCart = calculateTotalPrice()

        updatedCart.addDomainEvent(CartItemRemovedEvent(existingItem.productId.value))

        return updatedCart
    }

    private fun calculateTotalPrice(): Cart {
        return Cart(id, props.copy(
            totalPrice = props.cartItems
            .sumOf { it.price.times(it.quantity).value }
            .let { Money.create(it) },
            updatedAt = Instant.now()
        ))
    }

    val cartItems: MutableSet<CartItem> get() = props.cartItems
    val totalPrice: Money get() = props.totalPrice
    val createdAt: Instant get() = props.createdAt
    val updatedAt: Instant get() = props.updatedAt
    val kioskId: KioskId get() = props.kioskId
}
