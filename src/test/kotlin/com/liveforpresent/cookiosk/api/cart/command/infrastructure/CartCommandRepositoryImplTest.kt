package com.liveforpresent.cookiosk.api.cart.command.infrastructure

import com.liveforpresent.cookiosk.api.cart.command.domain.Cart
import com.liveforpresent.cookiosk.api.cart.command.domain.CartProps
import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItem
import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItemProps
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartId
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartItemId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.Instant

class CartCommandRepositoryImplTest: BehaviorSpec({
    val cartCommandJpaRepository = mockk<CartCommandJpaRepository>()
    val cartCommandRepositoryImpl = CartCommandRepositoryImpl(cartCommandJpaRepository)

    given("a cart") {
        val cartId = CartId(1L)
        val cartItemId = CartItemId(1L)
        val kioskId = KioskId(1L)

        val cartItemProps = CartItemProps(
            name = "a",
            price = Money.create(1),
            imageUrl = ImageUrl.create("url"),
            quantity = 1,
            productId = ProductId(1L)
        )

        val cartItem = CartItem.create(cartItemId, cartItemProps)

        val cartProps = CartProps(
            totalPrice = Money.create(1),
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            kioskId = kioskId,
            cartItems = mutableSetOf<CartItem>(cartItem),
        )

        val cart = Cart.create(cartId, cartProps)

        val entityReturnedFromJpa = CartEntity(
            id = cartId.value,
            kioskId = cart.kioskId.value,
            totalPrice = cart.totalPrice.value,
            createdAt = cart.createdAt,
            updatedAt = cart.updatedAt,
            cartItems = mutableSetOf<CartItemEntity>(CartItemEntity.toPersistence(cartItem))
        )

        val domainReturnedFromJpa = CartEntity.toDomain(entityReturnedFromJpa)

        every { cartCommandJpaRepository.save(any<CartEntity>()) } returns entityReturnedFromJpa

        `when`("cart를 DB에 저장한다") {
            val result = cartCommandRepositoryImpl.save(cart)

            then("cart 정보를 return한다.") {
                result.id shouldBe domainReturnedFromJpa.id
                result.kioskId shouldBe domainReturnedFromJpa.kioskId
                result.totalPrice shouldBe domainReturnedFromJpa.totalPrice
                result.createdAt shouldBe domainReturnedFromJpa.createdAt
                result.updatedAt shouldBe domainReturnedFromJpa.updatedAt
                result.cartItems shouldBe domainReturnedFromJpa.cartItems
            }
        }
    }
})
