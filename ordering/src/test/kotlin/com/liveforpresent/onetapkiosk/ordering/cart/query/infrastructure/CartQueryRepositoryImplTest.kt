package com.liveforpresent.onetapkiosk.ordering.cart.query.infrastructure

import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CartId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.vo.CartItemId
import com.liveforpresent.onetapkiosk.ordering.cart.command.infrastructure.CartItemEntity
import com.liveforpresent.onetapkiosk.ordering.cart.command.infrastructure.CartRedisEntity
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.Instant
import java.util.Optional

class CartQueryRepositoryImplTest: DescribeSpec({
    val cartCrudRepository = mockk<CartQueryCrudRepository>()
    val cartQueryRedisRepositoryImpl = CartQueryRedisRepositoryImpl(cartCrudRepository)

    val cartId = CartId(1L)
    val cartItemId = CartItemId(1L)
    val kioskId = KioskId(1L)
    val productId = ProductId(1L)
    val now = Instant.now()

    describe("findById") {
        context("장바구니가 존재하는 경우") {
            val cartItemEntity = CartItemEntity(
                id = cartItemId.value,
                name = "name",
                price = 10,
                quantity = 1,
                imageUrl = "imageUrl",
                productId = productId.value
            )
            val cartEntity = CartRedisEntity(
                id = cartId.value,
                cartItems = mutableSetOf(cartItemEntity),
                kioskId = kioskId.value,
                totalPrice = 10,
                createdAt = now,
                updatedAt = now
            )

            every { cartCrudRepository.findById(cartId.value) } returns Optional.of(cartEntity)

            val result = cartQueryRedisRepositoryImpl.findById(cartId.value)

            it("CartModel을 반환해야 한다.") {
                result.id shouldBe cartId.value.toString()
                result.totalPrice shouldBe 10
                result.kioskId shouldBe kioskId.value
                result.cartItems.size shouldBe 1
            }

            it("CartItemModel 속성이 유효해야 한다.") {
                val cartItem = result.cartItems.find { it.id == cartItemId.value.toString() }

                cartItem?.name shouldBe cartItemEntity.name
                cartItem?.productId shouldBe cartItemEntity.productId.toString()
                cartItem?.quantity shouldBe cartItemEntity.quantity
            }

            it("findById가 한 번 호출되어야 한다.") {
                verify(exactly = 1) { cartCrudRepository.findById(cartId.value) }
            }
        }

        context("장바구니가 존재하지 않는 경우") {
            val invalidId = 999L

            every { cartCrudRepository.findById(invalidId) } returns Optional.empty()

            it("CustomException을 발생시켜야 한다") {
                val exception = shouldThrow<CustomException> {
                    cartQueryRedisRepositoryImpl.findById(invalidId)
                }

                exception.code shouldBe CustomExceptionCode.CART_NOT_FOUND
                exception.code.message shouldContain "장바구니가 존재하지 않습니다."
            }

            it("findById가 한 번 호출되어야 한다") {
                verify(exactly = 1) { cartCrudRepository.findById(invalidId) }
            }
        }
    }
})
