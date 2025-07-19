package com.liveforpresent.cookiosk.api.sale.query.application.handler

import com.liveforpresent.cookiosk.api.sale.query.application.query.GetSaleByCriteriaQuery
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleModel
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleQueryRepository
import org.springframework.stereotype.Service

@Service
class GetSaleByCriteriaHandler(
    private val saleQueryRepository: SaleQueryRepository
) {
    fun execute(query: GetSaleByCriteriaQuery): List<SaleModel> {
        return saleQueryRepository.findByCriteria(query.startAt, query.endAt, query.sortBy, query.kioskId)
    }
}
