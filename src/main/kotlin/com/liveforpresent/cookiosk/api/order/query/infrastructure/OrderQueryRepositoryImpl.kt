package com.liveforpresent.cookiosk.api.order.query.infrastructure

import com.liveforpresent.cookiosk.api.order.query.domain.OrderDetailModel
import com.liveforpresent.cookiosk.api.order.query.domain.OrderItemModel
import com.liveforpresent.cookiosk.api.order.query.domain.OrderModel
import com.liveforpresent.cookiosk.api.order.query.domain.OrderQueryRepository
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class OrderQueryRepositoryImpl(
    private val orderQueryJpaRepository: OrderQueryJpaRepository
): OrderQueryRepository {
    override fun findByCriteria(
        startAt: Instant?,
        endAt: Instant?,
        statuses: List<String>?,
        sortBy: String?,
    ): List<OrderModel> {
        return orderQueryJpaRepository.findByCriteria(startAt, endAt, statuses, sortBy)
    }

    override fun findById(orderId: Long): OrderDetailModel {
        val orderEntity = orderQueryJpaRepository.findById(orderId)
            .orElseThrow { IllegalArgumentException("해당 주문 내역이 존재하지 않습니다.") }

        return OrderDetailModel(
            id = orderEntity.id,
            status = orderEntity.status,
            totalPrice = orderEntity.totalPrice,
            kioskId = orderEntity.kioskId,
            orderItems = orderEntity.orderItems.map { OrderItemModel(
                it.id,
                it.name,
                it.price,
                it.quantity
            ) },
            createdAt = orderEntity.createdAt,
            updatedAt = orderEntity.updatedAt,
        )
    }
}
