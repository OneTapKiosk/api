package com.liveforpresent.cookiosk.api.order.query.presentation

import com.liveforpresent.cookiosk.api.order.query.application.handler.GetOrderByIdHandler
import com.liveforpresent.cookiosk.api.order.query.application.query.GetOrderByIdQuery
import com.liveforpresent.cookiosk.api.order.query.domain.OrderDetailModel
import com.liveforpresent.cookiosk.api.order.query.domain.OrderModel
import com.liveforpresent.cookiosk.api.order.query.domain.OrderQueryRepository
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/order")
class OrderQueryController(
    private val getOrderByIdHandler: GetOrderByIdHandler,
) {
    @GetMapping
    fun getOrderList(
        @RequestParam(required = false) startAt: Instant?,
        @RequestParam(required = false) endAt: Instant?,
        @RequestParam(required = false) statuses: List<String>?,
        @RequestParam(required = false) sortBy: String?,
    ): ResponseEntity<BaseApiResponse<List<OrderModel>>> {
        /*
        val result = orderQueryRepository.findByCriteria(startAt, endAt, statuses, sortBy)

        val response = BaseApiResponse<List<OrderModel>>(
            success = true,
            message = "주문 목록 조회 성공",
            data = result
        )
*/
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("{orderId}")
    fun getOrderDetail(@PathVariable("orderId") orderId: Long): ResponseEntity<BaseApiResponse<OrderDetailModel>> {
        val query = GetOrderByIdQuery(orderId)
        val result = getOrderByIdHandler.execute(query)

        val response = BaseApiResponse<OrderDetailModel>(
            success = true,
            message = "주문 상세 정보 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
