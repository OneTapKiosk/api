package com.liveforpresent.onetapkiosk.ordering.cart.command.domain.entity

import com.liveforpresent.onetapkiosk.common.core.domain.BaseEntity
import com.liveforpresent.onetapkiosk.common.core.domain.vo.ImageUrl
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.vo.CartItemId

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
        require(props.name.isNotBlank()) {
            throw CustomException(
                CustomExceptionCode.CART_ITEM_NAME_EMPTY,
                "[CartItem] 장바구니 내 상품명은 필수입니다."
            ) }
        require(props.name.length < 32) {
            throw CustomException(
                CustomExceptionCode.CART_ITEM_NAME_LENGTH_EXCEEDED,
                "[CartItem] 장바구니 내 상품명은 최대 31자 입니다."
            ) }

        require(props.price.value >= 0) {
            throw CustomException(
                CustomExceptionCode.CART_ITEM_PRICE_NEGATIVE,
                "[CartItem] 장바구니 내 상품 가격은 음수일 수 없습니다."
            ) }

        require(props.quantity >= 0) {
            throw CustomException(
                CustomExceptionCode.CART_ITEM_QUANTITY_NEGATIVE,
                "[CartItem] 장바구니 내 상품 수량은 음수일 수 없습니다."
            ) }
    }

    fun increaseQuantity(): CartItem {
        // 재고 관련 로직이 필요할 수도
        return CartItem(id, props.copy(quantity = props.quantity + 1))
    }

    fun decreaseQuantity(): CartItem {
        // 재고 관련 로직이 필요할 수도
        require(props.quantity > 0) {
            throw CustomException(
                CustomExceptionCode.CART_ITEM_QUANTITY_NON_POSITIVE,
                "[CartItem] 장바구니 네 감소시킬 상품 수량은 0보다 커야 합니다."
            ) }

        return CartItem(id, props.copy(quantity = props.quantity - 1))
    }

    val name: String get() = props.name
    val price: Money get() = props.price
    val imageUrl: ImageUrl get() = props.imageUrl
    val quantity: Int get() = props.quantity
    val productId: ProductId get() = props.productId

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CartItem

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}
