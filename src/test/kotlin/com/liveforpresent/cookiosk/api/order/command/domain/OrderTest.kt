package com.liveforpresent.cookiosk.api.order.command.domain

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItem
import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItemProps
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderId
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderItemId
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderStatus
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeOneOf
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import java.time.Instant

class OrderTest : DescribeSpec({
    describe("Order") {
        val orderId = OrderId(1L)
        val kioskId = KioskId(1L)
        val productId = ProductId(1L)
        val orderItemId = OrderItemId(1L)
        val now = Instant.now()

        val orderItemProps = OrderItemProps(
                name = "name",
                price = Money.create(10),
                quantity = 1,
                productId = productId,
            )

        val orderProps = OrderProps(
                orderItems = mutableSetOf<OrderItem>(),
                status = OrderStatus("CREATED"),
                totalPrice = Money.create(10),
                createdAt = now,
                updatedAt = now,
                kioskId = kioskId
            )

        describe("create") {
            val orderItem = OrderItem.create(orderItemId, orderItemProps)
            val order = Order.create(orderId, orderProps.copy(
                orderItems = mutableSetOf<OrderItem>(orderItem),
            ))

            order.id shouldBe orderId
            order.orderStatus shouldBe OrderStatus.CREATED
            order.totalPrice.value shouldBe 10
            order.createdAt shouldBe now
            order.updatedAt shouldBe now
            order.orderItems.size shouldBe 1
        }

        describe("processPayment") {
            context("현재 상태가 CREATED인 경우") {
                val orderItem = OrderItem.create(orderItemId, orderItemProps)
                val order = Order.create(orderId, orderProps.copy(
                    orderItems = mutableSetOf<OrderItem>(orderItem),
                ))

                order.orderStatus shouldBe OrderStatus.CREATED

                val updatedOrder = order.processPayment()

                updatedOrder.id shouldBe orderId
                updatedOrder.orderStatus shouldBe OrderStatus.PENDING
            }

            context("현재 상태가 CREATED가 아닌 경우") {
                val orderItem = OrderItem.create(orderItemId, orderItemProps)
                val order = Order.create(orderId, orderProps.copy(
                    status = OrderStatus.PENDING,
                    orderItems = mutableSetOf<OrderItem>(orderItem),
                ))

                order.orderStatus shouldNotBe OrderStatus.CREATED

                shouldThrow<IllegalArgumentException> {
                    order.processPayment()
                }.message shouldContain "상태에서는 결제 단계로 넘어갈 수 없습니다."
            }
        }

        describe("finishAsSuccess") {
            context("현재 상태가 PENDING인 경우") {
                val orderItem = OrderItem.create(orderItemId, orderItemProps)
                val order = Order.create(orderId, orderProps.copy(
                    orderItems = mutableSetOf<OrderItem>(orderItem),
                    status = OrderStatus.PENDING,
                ))

                order.orderStatus shouldBe OrderStatus.PENDING

                val updatedOrder = order.finishAsSuccess()

                updatedOrder.id shouldBe orderId
                updatedOrder.orderStatus shouldBe OrderStatus.SUCCESS
            }

            context("현재 상태가 PENDING이 아닌 경우") {
                val orderItem = OrderItem.create(orderItemId, orderItemProps)
                val order = Order.create(orderId, orderProps.copy(
                    orderItems = mutableSetOf<OrderItem>(orderItem),
                    status = OrderStatus.CREATED
                ))

                order.orderStatus shouldNotBe OrderStatus.PENDING

                shouldThrow<IllegalArgumentException> {
                    order.finishAsSuccess()
                }.message shouldContain "상태에서는 주문 성공이 불가능합니다."
            }
        }

        describe("finishAsFailure") {
            context("현재 상태가 CREATED 혹은 PENDING인 경우") {
                val orderItem = OrderItem.create(orderItemId, orderItemProps)
                val order = Order.create(orderId, orderProps.copy(
                    orderItems = mutableSetOf<OrderItem>(orderItem),
                    status = OrderStatus.CREATED
                ))

                order.orderStatus shouldBeOneOf setOf(OrderStatus.PENDING, OrderStatus.CREATED)

                val updatedOrder = order.finishAsReject()

                updatedOrder.id shouldBe orderId
                updatedOrder.orderStatus shouldBe OrderStatus.REJECTED
            }
        }

        describe("finishAsCancel") {
            context("현재 상태가 CREATED 혹은 PENDING인 경우") {
                val orderItem = OrderItem.create(orderItemId, orderItemProps)
                val order = Order.create(orderId, orderProps.copy(
                    orderItems = mutableSetOf<OrderItem>(orderItem),
                    status = OrderStatus.CREATED
                ))

                order.orderStatus shouldBeOneOf setOf(OrderStatus.PENDING, OrderStatus.CREATED)

                val updatedOrder = order.finishAsCancel()

                updatedOrder.id shouldBe orderId
                updatedOrder.orderStatus shouldBe OrderStatus.CANCELLED
            }
        }
    }
})
