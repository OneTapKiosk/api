package com.liveforpresent.onetapkiosk.product.query.application.handler

import com.liveforpresent.onetapkiosk.product.query.domain.ProductQueryRepository
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
