package com.liveforpresent.onetapkiosk.ordering.order.command.application.handler

import com.liveforpresent.onetapkiosk.ordering.order.command.application.command.UpdateOrderStatusCommand
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.OrderCommandRepository
import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProcessPaymentHandler(
    private val orderCommandRepository: OrderCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: UpdateOrderStatusCommand) {
        val order = orderCommandRepository.findOne(command.orderId)

        val updatedOrder = order.processPayment()

        orderCommandRepository.save(updatedOrder)

        eventPublisher.publish(updatedOrder)
    }
}
