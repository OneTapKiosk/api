package com.liveforpresent.cookiosk.api.cart.command.application

import com.liveforpresent.cookiosk.api.cart.command.application.command.RemoveCartItemCommand
import com.liveforpresent.cookiosk.api.cart.command.application.handler.RemoveItemHandler
import com.liveforpresent.cookiosk.api.cart.command.domain.Cart
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRedisRepository
import com.liveforpresent.cookiosk.api.cart.command.domain.CartProps
import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItem
import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItemProps
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
import io.mockk.*
import java.time.Instant

class RemoveItemHandlerTest : DescribeSpec({
    val cartCommandRedisRepository = mockk<CartCommandRedisRepository>()
    val eventPublisher = mockk<DomainEventPublisher>()
    val removeItemHandler = RemoveItemHandler(cartCommandRedisRepository, eventPublisher)
    mockkObject(SnowflakeIdUtil)

    beforeTest {
        clearMocks(cartCommandRedisRepository, eventPublisher, answers = false)
    }

    describe("removeItemHandler의 execute 메서드") {
        val now = Instant.now()
        val cartId = CartId(1L)
        val targetCartItemId = CartItemId(1L)
        val kioskId = KioskId(1L)
        val productId = ProductId(1L)

        context("장바구니 내 상품 수량이 1 초과인 경우") {
            val existingCart = Cart.create(
                cartId, CartProps(
                    cartItems = mutableSetOf(
                        CartItem.create(
                            targetCartItemId, CartItemProps(
                                name = "name",
                                price = Money.create(1),
                                quantity = 2,
                                imageUrl = ImageUrl.create("url"),
                                productId = productId
                            )
                        )
                    ),
                    totalPrice = Money.create(1),
                    createdAt = now,
                    updatedAt = now,
                    kioskId = kioskId
                )
            )
            val updatedCart = existingCart.removeItem(targetCartItemId)

            val command = RemoveCartItemCommand(
                cartId = cartId.value,
                cartItemId = targetCartItemId.value,
            )

            every { cartCommandRedisRepository.findById(any()) } returns existingCart
            every { cartCommandRedisRepository.save(any()) } returns Unit
            every { eventPublisher.publish<Identifier<Long>>(any()) } returns Unit

            it("장바구니에서 해당 상품의 수량이 1 감소되어야 한다. 업데이트된 장바구니 저장, 이벤트를 발행해야 한다.") {
                removeItemHandler.execute(command)
                verify(exactly = 1) { cartCommandRedisRepository.findById(cartId.value) }
                verify(exactly = 1) {
                    cartCommandRedisRepository.save(withArg {
                        it.cartItems.any { item -> item.id == targetCartItemId && item.quantity == 1 }
                    })
                }
                verify(exactly = 1) { eventPublisher.publish(updatedCart) }
            }
        }

        context("장바구니 내 상품 수량이 1인 경우") {
            val existingCart = Cart.create(
                cartId, CartProps(
                    cartItems = mutableSetOf(
                        CartItem.create(
                            targetCartItemId, CartItemProps(
                                name = "name",
                                price = Money.create(1),
                                quantity = 1,
                                imageUrl = ImageUrl.create("url"),
                                productId = productId
                            )
                        )
                    ),
                    totalPrice = Money.create(1),
                    createdAt = now,
                    updatedAt = now,
                    kioskId = kioskId
                )
            )

            val command = RemoveCartItemCommand(
                cartId = cartId.value,
                cartItemId = targetCartItemId.value,
            )

            every { cartCommandRedisRepository.findById(any()) } returns existingCart
            every { cartCommandRedisRepository.save(any()) } returns Unit
            every { eventPublisher.publish<Identifier<Long>>(any()) } returns Unit

            it("장바구니에서 해당 상품이 삭제된다. 업데이트된 장바구니 저장, 이벤트를 발행해야 한다.") {
                removeItemHandler.execute(command)
                verify(exactly = 1) { cartCommandRedisRepository.findById(cartId.value) }
                verify(exactly = 1) {
                    cartCommandRedisRepository.save(withArg {
                        it.cartItems.any { item -> item.id == targetCartItemId } shouldBe false
                    })
                }
            }
        }
    }
})
