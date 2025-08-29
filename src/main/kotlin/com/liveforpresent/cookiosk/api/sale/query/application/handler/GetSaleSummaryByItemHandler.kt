package com.liveforpresent.cookiosk.api.sale.query.application.handler

import com.liveforpresent.cookiosk.api.sale.query.application.query.GetSaleSummaryByItemQuery
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleByItemModel
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleByItemQueryRepository
import org.springframework.stereotype.Service

@Service
class GetSaleSummaryByItemHandler(
    private val saleQueryByItemRepository: SaleByItemQueryRepository
) {
    fun execute(query: GetSaleSummaryByItemQuery): List<SaleByItemModel> {
        return saleQueryByItemRepository.findByItem(
            query.startAt,
            query.endAt,
            query.kioskId
        )
    }
}
