package com.liveforpresent.onetapkiosk.ordering.sale.query.presentation

import com.liveforpresent.onetapkiosk.common.core.presentation.BaseApiResponse
import com.liveforpresent.onetapkiosk.ordering.sale.query.application.handler.GetSaleByCriteriaHandler
import com.liveforpresent.onetapkiosk.ordering.sale.query.application.handler.GetSaleSummaryByItemHandler
import com.liveforpresent.onetapkiosk.ordering.sale.query.application.query.GetSaleByCriteriaQuery
import com.liveforpresent.onetapkiosk.ordering.sale.query.application.query.GetSaleSummaryByItemQuery
import com.liveforpresent.onetapkiosk.ordering.sale.query.domain.SaleByItemModel
import com.liveforpresent.onetapkiosk.ordering.sale.query.domain.SaleModel
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
    private val getSaleByCriteriaHandler: GetSaleByCriteriaHandler,
    private val getSaleSummaryByItemHandler: GetSaleSummaryByItemHandler,
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
        @RequestParam(required = false) startAt: Instant?,
        @RequestParam(required = false) endAt: Instant?,
        @RequestParam(required = false) sortBy: String?,
        @RequestParam(required = false) kioskId: Long,
    ): ResponseEntity<BaseApiResponse<List<SaleByItemModel>>> {
        val query = GetSaleSummaryByItemQuery(startAt, endAt, kioskId)
        val result = getSaleSummaryByItemHandler.execute(query)

        val response = BaseApiResponse<List<SaleByItemModel>>(
            success = true,
            message = "품목별 매출 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
