package com.liveforpresent.cookiosk.api.sale.query.application.handler

import com.liveforpresent.cookiosk.api.sale.query.application.query.GetSaleSummaryByItemQuery
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleByItemModel
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleQueryRepository
import org.springframework.stereotype.Service

@Service
class GetSaleSummaryByItemHandler(
    private val saleQueryRepository: SaleQueryRepository
) {
    fun execute(query: GetSaleSummaryByItemQuery): List<SaleByItemModel> {
        return saleQueryRepository.findSummaryByItem(
            query.startAt,
            query.endAt,
            query.kioskId
        )
    }
}
