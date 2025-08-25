package com.liveforpresent.cookiosk.api.cart.command.application

import com.liveforpresent.cookiosk.api.cart.command.application.command.AddCartItemCommand
import com.liveforpresent.cookiosk.api.cart.command.application.handler.AddItemHandler
import com.liveforpresent.cookiosk.api.cart.command.domain.Cart
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRedisRepository
import com.liveforpresent.cookiosk.api.cart.command.domain.CartProps
import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItem
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartId
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartItemId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import com.liveforpresent.cookiosk.shared.core.domain.vo.Identifier
import com.liveforpresent.cookiosk.shared.core.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify

class AddItemHandlerTest: DescribeSpec({
    val cartCommandRedisRepository = mockk<CartCommandRedisRepository>()
    val eventPublisher = mockk<DomainEventPublisher>()
    val addItemHandler = AddItemHandler(cartCommandRedisRepository, eventPublisher)
    mockkObject(SnowflakeIdUtil)

    describe("AddItemHandler") {
        it("장바구니에 상품을 추가하면, 관련 메서드 호출하고 생성된 cartItemId를 반환한다.") {
            val cartId = CartId(1L)
            val productId = ProductId(1L)
            val price = Money.create(1)
            val imageUrl = ImageUrl.create("http://some.url")

            val command = AddCartItemCommand(
                cartId = cartId.value,
                name = "cart",
                price = price.value,
                imageUrl = imageUrl.value,
                quantity = 1,
                productId = productId.value
            )

            val cart = mockk<Cart>()
            val cartItemId = CartItemId(1L)
            val updatedCart = mockk<Cart>()

            every { cartCommandRedisRepository.findById(cartId.value) } returns cart
            every { SnowflakeIdUtil.generateId() } returns cartId.value
            every { cart.addItem(any()) } returns updatedCart
            every { eventPublisher.publish<Identifier<Long>>(any()) } returns Unit
            every { cartCommandRedisRepository.save(any()) } returns Unit

            val result = addItemHandler.execute(command)

            result.cartItemId.toLong() shouldBe cartItemId.value
            verify(exactly = 1) { cartCommandRedisRepository.findById(cartId.value) }
            verify(exactly = 1) { cartCommandRedisRepository.save(updatedCart) }
        }
    }
})
