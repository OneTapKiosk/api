package com.liveforpresent.onetapkiosk.ordering.cart.command.application

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CartId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.infrastructure.util.SnowflakeIdUtil
import com.liveforpresent.onetapkiosk.ordering.cart.command.application.command.CreateCartCommand
import com.liveforpresent.onetapkiosk.ordering.cart.command.application.handler.CreateCartHandler
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.Cart
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.CartCommandRedisRepository
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
