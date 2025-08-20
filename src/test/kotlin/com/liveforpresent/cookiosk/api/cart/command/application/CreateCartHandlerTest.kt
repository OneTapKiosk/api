package com.liveforpresent.cookiosk.api.cart.command.application

import com.liveforpresent.cookiosk.api.cart.command.application.command.CreateCartCommand
import com.liveforpresent.cookiosk.api.cart.command.application.handler.CreateCartHandler
import com.liveforpresent.cookiosk.api.cart.command.domain.Cart
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRedisRepository
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify

class CreateCartHandlerTest: DescribeSpec({
    val cartCommandRedisRepository: CartCommandRedisRepository = mockk()
    val createCartHandler = CreateCartHandler(cartCommandRedisRepository)
    mockkObject(SnowflakeIdUtil)

    describe("createCartHandler") {
        context("execute 메서드 호출") {
            val primitiveCartId = 1L
            val cartId = CartId(primitiveCartId)
            val kioskId = KioskId(1L)
            val command = CreateCartCommand(kioskId.value)

            every { SnowflakeIdUtil.generateId() } returns primitiveCartId
            every { cartCommandRedisRepository.save(any()) } returns Unit

            val result = createCartHandler.execute(command)

            it("새로운 Cart가 생성되어야 한다") {
                verify(exactly = 1) { cartCommandRedisRepository.save(any()) }
                result.cartId.toLong() shouldBe primitiveCartId
            }

            it("Cart 속성들이 유효해야 한다.") {
                val slot = mutableListOf<Cart>()
                verify(exactly = 1) { cartCommandRedisRepository.save(capture(slot)) }

                val capturedCart = slot.first()

                capturedCart.kioskId shouldBe kioskId
                capturedCart.id shouldBe cartId
                capturedCart.cartItems shouldBe mutableSetOf()
                capturedCart.totalPrice shouldBe Money.create(0)
            }
        }
    }
})
