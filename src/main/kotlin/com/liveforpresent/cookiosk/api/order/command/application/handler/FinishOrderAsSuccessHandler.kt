package com.liveforpresent.cookiosk.api.order.command.application.handler

import com.liveforpresent.cookiosk.api.order.command.application.command.UpdateOrderStatusCommand
import com.liveforpresent.cookiosk.api.order.command.domain.OrderCommandRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class FinishOrderAsSuccessHandler(
    private val orderCommandRepository: OrderCommandRepository,
) {
    @Transactional
    fun execute(command: UpdateOrderStatusCommand) {
        val order = orderCommandRepository.findOne(command.orderId)

        val updatedOrder = order.finishAsSuccess()

        orderCommandRepository.save(updatedOrder)
    }
}
