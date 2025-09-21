package com.liveforpresent.onetapkiosk.ordering.cart.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.vo.ImageUrl
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CartId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.entity.CartItem
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.entity.CartItemProps
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.event.CartItemAddedEvent
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.event.CartItemRemovedEvent
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.vo.CartItemId
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.Instant

class CartTest : DescribeSpec({
    describe("Cart") {
        val cartId = CartId(1L)
        val kioskId = KioskId(1L)
        val now = Instant.now()

        describe("create") {
            val props = CartProps(
                cartItems = mutableSetOf<CartItem>(),
                totalPrice = Money.create(0),
                createdAt = now,
                updatedAt = now,
                kioskId = kioskId
            )

            val cart = Cart.create(cartId, props)

            cart.id shouldBe cartId
            cart.cartItems.isEmpty() shouldBe true
            cart.totalPrice.value shouldBe 0
            cart.createdAt shouldBe now
            cart.updatedAt shouldBe now
            cart.kioskId shouldBe kioskId
        }

        describe("addItem") {
            context("장바구니에 존재하지 않는 새로운 상품을 추가하는 경우") {
                it("새로운 상품이 장바구니에 quantity=1로 추가되어야 한다.") {
                    val props = CartProps(
                        cartItems = mutableSetOf<CartItem>(),
                        totalPrice = Money.create(0),
                        createdAt = now,
                        updatedAt = now,
                        kioskId = kioskId
                    )

                    val cart = Cart.create(cartId, props)

                    val newCartItemId = CartItemId(1L)
                    val newCartItemProps = CartItemProps(
                        name = "new item",
                        price = Money.create(1),
                        quantity = 1,
                        imageUrl = ImageUrl.create("new item image"),
                        productId = ProductId(1L)
                    )
                    val newCartItem = CartItem.create(newCartItemId, newCartItemProps)

                    val updatedCart = cart.addItem(newCartItem)

                    updatedCart.cartItems.size shouldBe 1
                    updatedCart.cartItems.first().quantity shouldBe 1
                    updatedCart.totalPrice.value shouldBe 1
                    updatedCart.clearDomainEvents().first() shouldBe CartItemAddedEvent(newCartItem.productId.value)
                }
            }

            context("장바구니에 이미 존재하는 상품을 추가하는 경우") {
                it("이미 존재하는 상품의 수량이 1 증가해야 한다.") {

                    val existingCartItemId = CartItemId(1L)
                    val existingCartItemProps = CartItemProps(
                        name = "new item",
                        price = Money.create(1),
                        quantity = 1,
                        imageUrl = ImageUrl.create("new item image"),
                        productId = ProductId(1L)
                    )
                    val existingCartItem = CartItem.create(existingCartItemId, existingCartItemProps)

                    val props = CartProps(
                        cartItems = mutableSetOf<CartItem>(existingCartItem),
                        totalPrice = Money.create(0),
                        createdAt = now,
                        updatedAt = now,
                        kioskId = kioskId
                    )

                    val cart = Cart.create(cartId, props)

                    val updatedCart = cart.addItem(existingCartItem)

                    updatedCart.cartItems.size shouldBe 1
                    updatedCart.cartItems.first().quantity shouldBe 2
                    updatedCart.totalPrice.value shouldBe 2
                    updatedCart.clearDomainEvents()
                        .first() shouldBe CartItemAddedEvent(existingCartItem.productId.value)
                }
            }
        }

        describe("removeItem") {
            context("삭제하려는 상품 수량이 2 이상인 경우") {
                it("해당 상품의 수량이 1이 되어야 한다.") {
                    val existingCartItemId = CartItemId(1L)
                    val existingCartItemProps = CartItemProps(
                        name = "new item",
                        price = Money.create(1),
                        quantity = 2,
                        imageUrl = ImageUrl.create("new item image"),
                        productId = ProductId(1L)
                    )
                    val existingCartItem = CartItem.create(existingCartItemId, existingCartItemProps)

                    val props = CartProps(
                        cartItems = mutableSetOf<CartItem>(existingCartItem),
                        totalPrice = Money.create(0),
                        createdAt = now,
                        updatedAt = now,
                        kioskId = kioskId
                    )

                    val cart = Cart.create(cartId, props)

                    val updatedCart = cart.removeItem(existingCartItemId)

                    updatedCart.cartItems.size shouldBe 1
                    updatedCart.cartItems.first().quantity shouldBe 1
                    updatedCart.totalPrice.value shouldBe 1
                    updatedCart.clearDomainEvents()
                        .first() shouldBe CartItemRemovedEvent(existingCartItem.productId.value)
                }
            }
        }
    }
})
