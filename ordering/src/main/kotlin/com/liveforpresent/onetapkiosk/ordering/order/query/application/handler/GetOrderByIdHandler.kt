package com.liveforpresent.onetapkiosk.ordering.order.query.application.handler

import com.liveforpresent.onetapkiosk.ordering.order.query.application.query.GetOrderByIdQuery
import com.liveforpresent.onetapkiosk.ordering.order.query.domain.OrderDetailModel
import com.liveforpresent.onetapkiosk.ordering.order.query.domain.OrderQueryRepository
import org.springframework.stereotype.Service

@Service
class GetOrderByIdHandler(
    private val orderQueryRepository: OrderQueryRepository
) {
    fun execute(query: GetOrderByIdQuery): OrderDetailModel {
        return orderQueryRepository.findById(query.orderId)
    }
}
