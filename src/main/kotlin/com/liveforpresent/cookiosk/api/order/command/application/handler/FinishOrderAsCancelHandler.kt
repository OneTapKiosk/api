package com.liveforpresent.cookiosk.api.order.command.application.handler

import com.liveforpresent.cookiosk.api.order.command.application.command.UpdateOrderStatusCommand
import com.liveforpresent.cookiosk.api.order.command.domain.OrderCommandRepository
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class FinishOrderAsCancelHandler(
    private val orderCommandRepository: OrderCommandRepository,
    private val eventPublisher: ApplicationEventPublisher
) {
    @Transactional
    fun execute(command: UpdateOrderStatusCommand) {
        val order = orderCommandRepository.findOne(command.orderId)

        val updatedOrder = order.finishAsCancel()

        orderCommandRepository.save(updatedOrder)

        eventPublisher.publishEvent(updatedOrder)
    }
}
