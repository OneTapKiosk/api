package com.liveforpresent.onetapkiosk.ordering.order.command.presentation

import com.liveforpresent.onetapkiosk.ordering.order.command.application.command.UpdateOrderStatusCommand
import com.liveforpresent.onetapkiosk.ordering.order.command.application.dto.CreateOrderResDto
import com.liveforpresent.onetapkiosk.ordering.order.command.presentation.dto.request.CreateOrderReqDto
import com.liveforpresent.onetapkiosk.common.core.presentation.BaseApiResponse
import com.liveforpresent.onetapkiosk.ordering.order.command.application.handler.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderCommandController(
    private val createOrderHandler: CreateOrderHandler,
    private val finishOrderAsSuccessHandler: FinishOrderAsSuccessHandler,
    private val finishOrderAsRejectHandler: FinishOrderAsRejectHandler,
    private val finishOrderAsCancelHandler: FinishOrderAsCancelHandler,
    private val processPaymentHandler: ProcessPaymentHandler
) {
    @PostMapping
    fun createOrder(@RequestBody reqDto: CreateOrderReqDto): ResponseEntity<BaseApiResponse<CreateOrderResDto>> {
        val command = reqDto.toCommand()
        val data = createOrderHandler.execute(command)

        val response = BaseApiResponse<CreateOrderResDto>(
            success = true,
            message = "주문 생성 성공",
            data = data
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PatchMapping("/{orderId}/process-payment")
    fun processPayment(@PathVariable orderId: Long): ResponseEntity<BaseApiResponse<Unit>> {
        val command = UpdateOrderStatusCommand(orderId)

        processPaymentHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "결제 진행"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @PatchMapping("/{orderId}/success")
    fun finishOrderAsSuccess(@PathVariable orderId: Long): ResponseEntity<BaseApiResponse<Unit>> {
        val command = UpdateOrderStatusCommand(orderId)

        finishOrderAsSuccessHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "주문 성공"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @PatchMapping("/{orderId}/reject")
    fun finishOrderAsReject(@PathVariable orderId: Long): ResponseEntity<BaseApiResponse<Unit>> {
        val command = UpdateOrderStatusCommand(orderId)

        finishOrderAsRejectHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "주문 실패"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }


    @PatchMapping("/{orderId}/cancel")
    fun finishOrderAsCancel(@PathVariable orderId: Long): ResponseEntity<BaseApiResponse<Unit>> {
        val command = UpdateOrderStatusCommand(orderId)

        finishOrderAsCancelHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "주문 취소"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
