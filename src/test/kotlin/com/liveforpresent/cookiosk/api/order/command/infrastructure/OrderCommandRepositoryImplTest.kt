package com.liveforpresent.cookiosk.api.order.command.infrastructure

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.order.command.domain.Order
import com.liveforpresent.cookiosk.api.order.command.domain.OrderProps
import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItem
import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItemProps
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderId
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderItemId
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderStatus
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.Instant

class OrderCommandRepositoryImplTest: BehaviorSpec({
    val orderCommandJpaRepository = mockk<OrderCommandJpaRepository>()
    val orderCommandRepositoryImpl = OrderCommandRepositoryImpl(orderCommandJpaRepository)

    given("a order") {
        val orderId = OrderId(1L)
        val orderItemId = OrderItemId(1L)

        val orderItemProps = OrderItemProps(
            name = "name",
            price = Money.create(1),
            quantity = 1
        )

        val orderItem = OrderItem.create(orderItemId, orderItemProps)

        val orderProps = OrderProps(
            status = OrderStatus.PENDING,
            totalPrice = Money.create(1),
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            kioskId = KioskId(1L),
            orderItems = mutableSetOf(orderItem)
        )

        val order = Order.create(orderId, orderProps)

        val entityReturnedFromJpa = OrderEntity(
            id = order.id.value,
            status = order.orderStatus.value,
            totalPrice = order.totalPrice.value,
            createdAt = order.createdAt,
            updatedAt = order.updatedAt,
            kioskId = order.kioskId.value,
            orderItems = mutableSetOf(OrderItemEntity.toPersistence(orderItem))
        )

        val domainReturnedFromJpa = OrderEntity.toDomain(entityReturnedFromJpa)

        every { orderCommandJpaRepository.save(any<OrderEntity>()) } returns entityReturnedFromJpa

        `when`("order를 DB에 저장한다") {
            val result = orderCommandRepositoryImpl.save(order)

            then("order 정보를 return 한다") {
                result.id shouldBe domainReturnedFromJpa.id
                result.orderStatus shouldBe domainReturnedFromJpa.orderStatus
                result.totalPrice shouldBe domainReturnedFromJpa.totalPrice
                result.orderItems shouldBe domainReturnedFromJpa.orderItems
                result.kioskId shouldBe domainReturnedFromJpa.kioskId
                result.createdAt shouldBe domainReturnedFromJpa.createdAt
                result.updatedAt shouldBe domainReturnedFromJpa.updatedAt
            }
        }
    }
})
