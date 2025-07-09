package com.liveforpresent.cookiosk.api.order.query.presentation

import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderQueryController {
    @GetMapping
    fun getOrderList(
        @RequestParam(required = false) orderStatus: List<String>?,
        @RequestParam(required = false) sort: String?,
        @RequestParam(required = false) startDate: String?,     // yyyy-mm-dd
        @RequestParam(required = false) endDate: String?,       // yyyy-mm-dd
    ): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("{orderId}")
    fun getOrderDetail(@PathVariable("orderId") orderId: String): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }
}
