package com.liveforpresent.onetapkiosk.ordering.cart.query.application

import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CartId
import com.liveforpresent.onetapkiosk.ordering.cart.query.application.handler.GetCartByIdHandler
import com.liveforpresent.onetapkiosk.ordering.cart.query.application.query.GetCartByIdQuery
import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartItemModel
import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartModel
import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartQueryRedisRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.Instant

class GetCartByIdHandlerTest : DescribeSpec({
    val cartQueryRedisRepository = mockk<CartQueryRedisRepository>()
    val getCartByIdHandler = GetCartByIdHandler(cartQueryRedisRepository)

    val cartId = CartId(1L)
    val now = Instant.now()

    describe("getCartByIdHandler execute") {
        context("장바구니가 존재하는 경우") {
            val cartModel = CartModel(
                id = "1L",
                cartItems = setOf<CartItemModel>(),
                totalPrice = 10,
                createdAt = now,
                updatedAt = now,
                kioskId = 1L
            )
            val query = GetCartByIdQuery(cartId.value)

            every { cartQueryRedisRepository.findById(any()) } returns cartModel

            it("CartModel을 반환해야 한다.") {
                val result = getCartByIdHandler.execute(query)

                verify(exactly = 1) { cartQueryRedisRepository.findById(query.cartId) }
                result shouldBe cartModel
            }
        }

        context("장바구니가 존재하지 않는 경우") {
            val invalidCartId = CartId(999L)
            val query = GetCartByIdQuery(invalidCartId.value)

            every { cartQueryRedisRepository.findById(any()) } throws IllegalStateException()

            it("IllegalArgumentException을 던진다") {
                shouldThrow<IllegalStateException> {
                    getCartByIdHandler.execute(query)
                }
                verify(exactly = 1) { cartQueryRedisRepository.findById(query.cartId) }
            }
        }
    }
})
