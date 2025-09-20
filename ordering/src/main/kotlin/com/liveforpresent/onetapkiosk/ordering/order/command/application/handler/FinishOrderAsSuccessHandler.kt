package com.liveforpresent.onetapkiosk.ordering.order.command.application.handler

import com.liveforpresent.onetapkiosk.ordering.order.command.application.command.UpdateOrderStatusCommand
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.OrderCommandRepository
import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FinishOrderAsSuccessHandler(
    private val orderCommandRepository: OrderCommandRepository,
    private val domainEventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: UpdateOrderStatusCommand) {
        val order = orderCommandRepository.findOne(command.orderId)

        val updatedOrder = order.finishAsSuccess()

        orderCommandRepository.save(updatedOrder)

        domainEventPublisher.publish(updatedOrder)
    }
}
