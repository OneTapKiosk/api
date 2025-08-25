package com.liveforpresent.cookiosk.api.cart.command.presentation

import com.liveforpresent.cookiosk.api.cart.command.application.dto.AddItemResDto
import com.liveforpresent.cookiosk.api.cart.command.application.dto.CreateCartResDto
import com.liveforpresent.cookiosk.api.cart.command.application.handler.AddItemHandler
import com.liveforpresent.cookiosk.api.cart.command.application.handler.CreateCartHandler
import com.liveforpresent.cookiosk.api.cart.command.application.handler.DeleteCartHandler
import com.liveforpresent.cookiosk.api.cart.command.application.handler.RemoveItemHandler
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartId
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartItemId
import com.liveforpresent.cookiosk.api.cart.command.presentation.dto.request.AddCartItemReqDto
import com.liveforpresent.cookiosk.api.cart.command.presentation.dto.request.CreateCartReqDto
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class CartCommandControllerTest : DescribeSpec({
    val createCartHandler = mockk<CreateCartHandler>()
    val deleteCartHandler = mockk<DeleteCartHandler>()
    val addItemHandler = mockk<AddItemHandler>()
    val removeItemHandler = mockk<RemoveItemHandler>()
    val controller = CartCommandController(createCartHandler, deleteCartHandler, addItemHandler, removeItemHandler)

    val cartId = CartId(1L)
    val cartItemId = CartItemId(1L)
    val kioskId = KioskId(1L)

    describe("POST /cart") {
        it("장바구니 생성 성공 시 cartId를 반환해야 한다.") {
            val reqDto = CreateCartReqDto(kioskId.value)
            val resDto = CreateCartResDto(cartId.value.toString())

            every { createCartHandler.execute(any()) } returns resDto

            val result: ResponseEntity<BaseApiResponse<CreateCartResDto>> = controller.createCart(reqDto)

            result.statusCode shouldBe HttpStatus.CREATED
            result.body?.success shouldBe true
            result.body?.data shouldBe resDto
        }
    }

    describe("DELETE /cart/{cartId}") {
        it("장바구니 삭제 성공 시 OK 상태코드를 반환해야 한다.") {
            every { deleteCartHandler.execute(any()) } returns Unit

            val result: ResponseEntity<BaseApiResponse<Unit>> = controller.deleteCart(cartId.value)

            result.statusCode shouldBe HttpStatus.OK
            result.body?.success shouldBe true
            result.body?.data shouldBe null
        }
    }

    describe("POST /cart/{cartId}/items") {
        val reqDto = AddCartItemReqDto(
            name = "name",
            quantity = 1,
            price = 10,
            imageUrl = "imageUrl",
            productId = "1"
        )
        val resDto = AddItemResDto(cartId.value.toString())

        every { addItemHandler.execute(any()) } returns resDto

        val result: ResponseEntity<BaseApiResponse<AddItemResDto>> = controller.addItem(cartId.value, reqDto)

        result.statusCode shouldBe HttpStatus.CREATED
        result.body?.success shouldBe true
        result.body?.data shouldBe resDto
    }

    describe("DELETE /cart/{cartId}/items/{cartItemId}") {
        it("장바구니 상품 삭제에 성공 시 OK 상태 코드를 반환해야 한다.") {
            every { removeItemHandler.execute(any()) } returns Unit

            val result: ResponseEntity<BaseApiResponse<Unit>> = controller.deleteItem(cartId.value, cartItemId.value)

            result.statusCode shouldBe HttpStatus.OK
            result.body?.success shouldBe true
            result.body?.data shouldBe null
        }
    }
})
