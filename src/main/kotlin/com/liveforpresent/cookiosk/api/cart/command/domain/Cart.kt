package com.liveforpresent.cookiosk.api.cart.command.domain

import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItem
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartId
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartItemId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.shared.core.domain.AggregateRoot
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
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
        require(totalPrice.value >= 0) { "[Cart] 총 가격은 음수일 수 없습니다." }
    }

    fun addItem(item: CartItem): Cart {
        val existingItem = props.cartItems.find { it.id == item.id }

        if (existingItem == null) cartItems.add(item)
        else {
            props.cartItems.remove(existingItem)
            val updatedItem = existingItem.increaseQuantity()
            props.cartItems.add(updatedItem)
        }

        calculateTotalPrice()

        return Cart(id, props.copy(updatedAt = Instant.now()))
    }

    fun removeItem(item: CartItem): Cart {
        val existingItem = props.cartItems.find { it.id == item.id }
        require(existingItem != null) { "[Cart] 장바구니에 제거할 상품이 없습니다." }

        val removed = props.cartItems.remove(existingItem)
        require(removed) { "[Cart] 장바구니에서 상품 제거 실패: ${item.id.value}" }

        calculateTotalPrice()

        return Cart(id, props.copy(updatedAt = Instant.now()))
    }

    private fun calculateTotalPrice(): Cart {
        return Cart(id, props.copy(totalPrice = props.cartItems
            .sumOf { it.price.times(it.quantity).value }
            .let { Money.create(it) }))
    }

    val cartItems: MutableSet<CartItem> get() = props.cartItems
    val totalPrice: Money get() = props.totalPrice
    val createdAt: Instant get() = props.createdAt
    val updatedAt: Instant get() = props.updatedAt
    val kioskId: KioskId get() = props.kioskId
}
