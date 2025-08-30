package com.liveforpresent.cookiosk.api.sale.query.application.handler

import com.liveforpresent.cookiosk.api.sale.query.domain.SaleByItemQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class RefreshSaleByItemViewHandler(
    val saleByItemQueryRepository: SaleByItemQueryRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute() {
        saleByItemQueryRepository.refreshView()
    }
}
