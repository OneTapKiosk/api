package com.liveforpresent.cookiosk.api.product.query.application.handler

import com.liveforpresent.cookiosk.api.product.query.domain.ProductQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class RefreshProductViewHandler(
    private val productQueryRepository: ProductQueryRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute() {
        productQueryRepository.refreshView()
    }
}
