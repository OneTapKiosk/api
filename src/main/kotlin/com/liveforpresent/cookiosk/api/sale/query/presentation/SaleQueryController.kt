package com.liveforpresent.cookiosk.api.sale.query.presentation

import com.liveforpresent.cookiosk.api.sale.query.application.handler.GetSaleByCriteriaHandler
import com.liveforpresent.cookiosk.api.sale.query.application.query.GetSaleByCriteriaQuery
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleModel
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/sale")
class SaleQueryController(
    private val getSaleByCriteriaHandler: GetSaleByCriteriaHandler
) {
    @GetMapping
    fun getSaleByCriteria(
        @RequestParam(required = false) startAt: Instant?,
        @RequestParam(required = false) endAt: Instant?,
        @RequestParam(required = false) sortBy: String?,
        @RequestParam(required = false) kioskId: Long,
    ): ResponseEntity<BaseApiResponse<List<SaleModel>>> {
        val query = GetSaleByCriteriaQuery(startAt, endAt, sortBy, kioskId)
        val result = getSaleByCriteriaHandler.execute(query)

        val response = BaseApiResponse<List<SaleModel>>(
            success = true,
            message = "매출 정보 목록 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/product-summary")
    fun getSaleProductSummary(
        @RequestParam(required = false) startDate: String?,
        @RequestParam(required = false) endDate: String?,
        @RequestParam(required = false) sort: String?,
        @RequestParam(required = false) kioskId: Long,
    ): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }
}
