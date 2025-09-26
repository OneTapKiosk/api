package com.liveforpresent.onetapkiosk.ordering.order.command.application.handler

import com.liveforpresent.onetapkiosk.ordering.order.command.application.command.CreateOrderCommand
import com.liveforpresent.onetapkiosk.ordering.order.command.application.dto.CreateOrderResDto
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.Order
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.OrderCommandRepository
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.OrderProps
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.entity.OrderItem
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.entity.OrderItemProps
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.vo.OrderItemId
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.vo.OrderStatus
import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.OrderId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.common.core.infrastructure.util.SnowflakeIdUtil
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CreateOrderHandler(
    private val orderCommandRepository: OrderCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: CreateOrderCommand): CreateOrderResDto {
        val orderItems = command.orderItems.map {
            OrderItem.create(
                OrderItemId(SnowflakeIdUtil.generateId()),
                OrderItemProps(
                    name = it.name,
                    price = Money.create(it.price),
                    quantity = it.quantity,
                    productId = ProductId(it.productId)
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

        eventPublisher.publish(order)

        return CreateOrderResDto(order.id.value.toString())
    }
}
