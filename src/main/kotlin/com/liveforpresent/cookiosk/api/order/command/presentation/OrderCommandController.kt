package com.liveforpresent.cookiosk.api.order.command.presentation

import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class OrderCommandController {
    @PostMapping
    fun createOrder(@RequestBody createOrderReqDto: Any): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PatchMapping("/{orderId}")
    fun updateOrderStatus(@PathVariable orderId: String, @RequestBody updateOrderStatusReqDto: Any): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }
}
