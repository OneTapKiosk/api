package com.liveforpresent.onetapkiosk.ordering.sale.query.application.handler

import com.liveforpresent.onetapkiosk.ordering.sale.query.domain.SaleQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class RefreshSaleViewHandler(
    private val saleQueryRepository: SaleQueryRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute() {
        saleQueryRepository.refreshView()
    }
}
