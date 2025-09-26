package com.liveforpresent.onetapkiosk.ordering.order.query.application.handler

import com.liveforpresent.onetapkiosk.ordering.order.query.application.query.GetOrderListByCriteriaQuery
import com.liveforpresent.onetapkiosk.ordering.order.query.domain.OrderModel
import com.liveforpresent.onetapkiosk.ordering.order.query.domain.OrderQueryRepository
import org.springframework.stereotype.Service

@Service
class GetOrderListByCriteriaHandler(
    private val orderQueryRepository: OrderQueryRepository,
) {
    fun execute(query: GetOrderListByCriteriaQuery): List<OrderModel> {
        return orderQueryRepository.findByCriteria(query.startAt, query.endAt, query.statuses, query.sortBy)
    }
}
