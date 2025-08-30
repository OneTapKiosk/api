package com.liveforpresent.cookiosk.api.order.command.infrastructure

import com.liveforpresent.cookiosk.api.order.command.domain.Order
import com.liveforpresent.cookiosk.api.order.command.domain.OrderCommandRepository
import com.liveforpresent.cookiosk.shared.exception.CustomException
import com.liveforpresent.cookiosk.shared.exception.CustomExceptionCode
import org.springframework.stereotype.Repository

@Repository
class OrderCommandRepositoryImpl(
    private val orderCommandJpaRepository: OrderCommandJpaRepository
): OrderCommandRepository {
    override fun save(order: Order): Order {
        val orderEntity = OrderEntity.toPersistence(order)
        orderCommandJpaRepository.save(orderEntity)

        return order
    }

    override fun findOne(id: Long): Order {
        val orderEntity = orderCommandJpaRepository.findById(id).orElseThrow {
            CustomException(
                CustomExceptionCode.ORDER_NOT_FOUND,
                "[OrderCommandRepository] ${id}에 해당하는 주문이 존재하지 않습니다."
            )
        }

        return OrderEntity.toDomain(orderEntity)
    }
}
