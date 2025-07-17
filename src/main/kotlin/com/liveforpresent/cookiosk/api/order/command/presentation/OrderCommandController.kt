package com.liveforpresent.cookiosk.api.order.command.presentation

import com.liveforpresent.cookiosk.api.order.command.application.command.CreateOrderCommand
import com.liveforpresent.cookiosk.api.order.command.application.query.CreateOrderHandler
import com.liveforpresent.cookiosk.api.order.command.presentation.dto.request.CreateOrderReqDto
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderCommandController(
    private val createOrderHandler: CreateOrderHandler
) {
    @PostMapping
    fun createOrder(@RequestBody reqDto: CreateOrderReqDto): ResponseEntity<BaseApiResponse<Unit>> {
        val command = reqDto.toCommand()
        createOrderHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "주문 생성 성공",
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PatchMapping("/{orderId}")
    fun updateOrderStatus(@PathVariable orderId: String, @RequestBody updateOrderStatusReqDto: Any): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }
}
