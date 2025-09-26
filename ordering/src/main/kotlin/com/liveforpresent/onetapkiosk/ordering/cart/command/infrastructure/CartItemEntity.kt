package com.liveforpresent.onetapkiosk.ordering.cart.command.infrastructure

import com.liveforpresent.onetapkiosk.common.core.domain.vo.ImageUrl
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.entity.CartItem
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.entity.CartItemProps
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.vo.CartItemId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cart_item")
class CartItemEntity (
    @Id
    @Column(nullable = false)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @Column
    val imageUrl: String,

    @Column(nullable = false)
    val quantity: Int,

    @Column(nullable = false)
    val productId: Long
) {
    companion object {
        fun toPersistence(cartItem: CartItem): CartItemEntity {
            return CartItemEntity(
                id = cartItem.id.value,
                name = cartItem.name,
                price = cartItem.price.value,
                imageUrl = cartItem.imageUrl.value,
                quantity = cartItem.quantity,
                productId = cartItem.productId.value
            )
        }

        fun toDomain(cartItemEntity: CartItemEntity): CartItem {
            val props = CartItemProps(
                name = cartItemEntity.name,
                price = Money.create(cartItemEntity.price),
                imageUrl = ImageUrl.create(cartItemEntity.imageUrl),
                quantity = cartItemEntity.quantity,
                productId = ProductId(cartItemEntity.productId)
            )

            return CartItem.create(id = CartItemId(cartItemEntity.id), props = props)
        }
    }
}
