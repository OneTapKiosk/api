package com.liveforpresent.cookiosk.api.cart.command.domain.entity

import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartItemId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.BaseEntity
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money

class CartItem private constructor(
    id: CartItemId,
    private val props: CartItemProps
): BaseEntity<CartItemId>(id) {
    companion object {
        fun create(id: CartItemId, props: CartItemProps): CartItem {
            val cartItem = CartItem(id, props)
            cartItem.validate()

            return cartItem
        }
    }

    fun validate() {
        require(props.name.isNotBlank()) { "[CartItem] 장바구니 내 상품명은 필수입니다." }
        require(props.name.length < 32) { "[CartItem] 장바구니 내 상품명은 최대 31자 입니다."}

        require(props.price.value >= 0) { "[CartItem] 장바구니 내 상품 가격은 음수일 수 없습니다." }

        require(props.quantity > 0) { "[CartItem] 장바구니 내 상품 개수는 0보다 커야 합니다." }
    }

    fun increaseQuantity(): CartItem {
        // 재고 관련 로직이 필요할 수도
        return CartItem(id, props.copy(quantity = props.quantity + 1))
    }

    fun decreaseQuantity(): CartItem {
        // 재고 관련 로직이 필요할 수도
        require(props.quantity > 0) { "[CartItem] 장바구니 내 상품 개수는 0보다 커야 합니다." }

        return CartItem(id, props.copy(quantity = props.quantity - 1))
    }

    val name: String get() = props.name
    val price: Money get() = props.price
    val imageUrl: ImageUrl get() = props.imageUrl
    val quantity: Int get() = props.quantity

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CartItem

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}
