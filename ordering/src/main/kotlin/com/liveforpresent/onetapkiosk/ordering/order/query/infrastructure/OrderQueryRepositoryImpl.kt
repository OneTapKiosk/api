package com.liveforpresent.onetapkiosk.ordering.order.query.infrastructure

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.ordering.order.query.domain.OrderDetailModel
import com.liveforpresent.onetapkiosk.ordering.order.query.domain.OrderItemModel
import com.liveforpresent.onetapkiosk.ordering.order.query.domain.OrderModel
import com.liveforpresent.onetapkiosk.ordering.order.query.domain.OrderQueryRepository
import com.liveforpresent.onetapkiosk.ordering.shared.exception.OrderExceptionCode
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class OrderQueryRepositoryImpl(
    private val orderQueryJpaRepository: OrderQueryJpaRepository,
    private val em: EntityManager
): OrderQueryRepository {
    override fun findByCriteria(
        startAt: Instant?,
        endAt: Instant?,
        statuses: List<String>?,
        sortBy: String?,
    ): List<OrderModel> {
        val orderViews = orderQueryJpaRepository.findByCriteria(startAt, endAt, statuses, sortBy)

        return orderViews.map {
            OrderModel(
                id = it.id.toString(),
                status = it.status,
                totalPrice = it.totalPrice,
                kioskId = it.kioskId,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    override fun findById(orderId: Long): OrderDetailModel {
        val orderEntity = orderQueryJpaRepository.findById(orderId)
            .orElseThrow {
                CustomException(
                    OrderExceptionCode.ORDER_NOT_FOUND,
                    "[OrderQueryRepository] ${orderId}에 해당하는 주문이 존재하지 않습니다."
                )
            }

        return OrderDetailModel(
            id = orderEntity.id.toString(),
            status = orderEntity.status,
            totalPrice = orderEntity.totalPrice,
            kioskId = orderEntity.kioskId,
            orderItems = orderEntity.orderItems.map { OrderItemModel(
                it.id.toString(),
                it.name,
                it.price,
                it.quantity
            ) },
            createdAt = orderEntity.createdAt,
            updatedAt = orderEntity.updatedAt,
        )
    }

    override fun refreshView() {
        em.createNativeQuery("REFRESH MATERIALIZED VIEW CONCURRENTLY vw_orders")
            .executeUpdate()
    }
}
