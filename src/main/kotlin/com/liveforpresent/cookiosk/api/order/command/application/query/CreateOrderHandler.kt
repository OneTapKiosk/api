package com.liveforpresent.cookiosk.api.order.command.application.query

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.order.command.application.command.CreateOrderCommand
import com.liveforpresent.cookiosk.api.order.command.domain.Order
import com.liveforpresent.cookiosk.api.order.command.domain.OrderCommandRepository
import com.liveforpresent.cookiosk.api.order.command.domain.OrderProps
import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItem
import com.liveforpresent.cookiosk.api.order.command.domain.entity.OrderItemProps
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderId
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderItemId
import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderStatus
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CreateOrderHandler(
    private val orderCommandRepository: OrderCommandRepository,
) {
    @Transactional
    fun execute(command: CreateOrderCommand) {
        val orderItems = command.orderItems.map {
            OrderItem.create(
                OrderItemId(SnowflakeIdUtil.generateId()),
                OrderItemProps(
                    name = it.name,
                    price = Money.create(it.price),
                    quantity = it.quantity
                )
            )
        }.toMutableSet()

        val orderProps = OrderProps(
            orderItems = orderItems,
            status = OrderStatus.create("CREATED"),
            totalPrice = Money.create(0),
            kioskId = KioskId(command.kioskId),
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )

        val order = Order.create(OrderId(SnowflakeIdUtil.generateId()), orderProps)

        orderCommandRepository.save(order)
    }
}
