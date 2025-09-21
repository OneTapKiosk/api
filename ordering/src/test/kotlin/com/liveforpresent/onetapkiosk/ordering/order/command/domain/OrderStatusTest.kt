package com.liveforpresent.onetapkiosk.ordering.order.command.domain

import com.liveforpresent.onetapkiosk.ordering.order.command.domain.vo.OrderStatus
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class OrderStatusTest : DescribeSpec({
    describe("OrderStatus") {
        describe("canTransitionTo") {
            context("현재 상태가 CREATED인 경우") {
                it("PENDING, REJECTED, CANCELLED로의 전이는 가능해야 한다") {
                    OrderStatus.CREATED.canTransitionTo(OrderStatus.PENDING) shouldBe true
                    OrderStatus.CREATED.canTransitionTo(OrderStatus.REJECTED) shouldBe true
                    OrderStatus.CREATED.canTransitionTo(OrderStatus.CANCELLED) shouldBe true
                }

                it("SUCCESS 상태로의 전이는 불가능해야 한다") {
                    OrderStatus.CREATED.canTransitionTo(OrderStatus.SUCCESS) shouldBe false
                }
            }

            context("PENDING 상태일 때") {
                it("SUCCESS, REJECTED, CANCELLED로의 전이는 가능해야 한다") {
                    OrderStatus.PENDING.canTransitionTo(OrderStatus.SUCCESS) shouldBe true
                    OrderStatus.PENDING.canTransitionTo(OrderStatus.REJECTED) shouldBe true
                    OrderStatus.PENDING.canTransitionTo(OrderStatus.CANCELLED) shouldBe true
                }

                it("CREATED 상태로의 전이는 불가능해야 한다") {
                    OrderStatus.PENDING.canTransitionTo(OrderStatus.CREATED) shouldBe false
                }
            }

            context("SUCCESS, REJECTED, CANCELLED 상태일 때") {
                it("모든 상태로의 전이가 불가능해야 한다") {
                    OrderStatus.SUCCESS.canTransitionTo(OrderStatus.CREATED) shouldBe false
                    OrderStatus.SUCCESS.canTransitionTo(OrderStatus.PENDING) shouldBe false
                    OrderStatus.SUCCESS.canTransitionTo(OrderStatus.SUCCESS) shouldBe false

                    OrderStatus.REJECTED.canTransitionTo(OrderStatus.CREATED) shouldBe false
                    OrderStatus.REJECTED.canTransitionTo(OrderStatus.PENDING) shouldBe false

                    OrderStatus.CANCELLED.canTransitionTo(OrderStatus.CREATED) shouldBe false
                    OrderStatus.CANCELLED.canTransitionTo(OrderStatus.PENDING) shouldBe false
                }
            }
        }
    }
})
