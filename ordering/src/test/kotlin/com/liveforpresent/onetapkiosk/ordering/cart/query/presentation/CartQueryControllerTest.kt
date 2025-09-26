package com.liveforpresent.onetapkiosk.ordering.cart.query.presentation

import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CartId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.vo.CartItemId
import com.liveforpresent.onetapkiosk.ordering.cart.query.application.handler.GetCartByIdHandler
import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartItemModel
import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartModel
import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartQueryRedisRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.HttpStatus
import java.time.Instant


class CartQueryControllerTest : DescribeSpec({
    val getCartByIdHandler = mockk<GetCartByIdHandler>()
    val cartQueryRedisRepository = mockk<CartQueryRedisRepository>()
    val cartQueryController = CartQueryController(getCartByIdHandler)

    val cartId = CartId(1L)
    val cartItemId = CartItemId(1L)
    val productId = ProductId(1L)
    val kioskId = KioskId(1L)
    val now = Instant.now()

    describe("GET /cart/{cartId}") {
        context("장바구니가 존재하는 경우") {
            val cartItemModel = CartItemModel(
                id = cartItemId.value.toString(),
                name = "name",
                price = 10,
                quantity = 1,
                imageUrl = "url",
                productId = productId.value.toString()
            )

            val cartModel = CartModel(
                id = cartId.value.toString(),
                cartItems = setOf(cartItemModel),
                totalPrice = 10,
                createdAt = now,
                updatedAt = now,
                kioskId = kioskId.value,
            )

            every { getCartByIdHandler.execute(any()) } returns cartModel

            it("장바구니 데이터를 반환해야 한다.") {
                val result = cartQueryController.cartItems(cartId.value)

                result.statusCode shouldBe HttpStatus.OK
                result.body?.success shouldBe true
                result.body?.data shouldBe cartModel
            }
        }
    }
})
