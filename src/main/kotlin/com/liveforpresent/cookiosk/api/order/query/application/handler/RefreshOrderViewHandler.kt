package com.liveforpresent.cookiosk.api.order.query.application.handler

import com.liveforpresent.cookiosk.api.order.query.domain.OrderQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class RefreshOrderViewHandler(
    private val orderQueryRepository: OrderQueryRepository,
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute() {
        orderQueryRepository.refreshView()
    }
}
