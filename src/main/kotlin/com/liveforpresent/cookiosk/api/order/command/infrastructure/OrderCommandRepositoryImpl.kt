package com.liveforpresent.cookiosk.api.order.command.infrastructure

import com.liveforpresent.cookiosk.api.order.command.domain.Order
import com.liveforpresent.cookiosk.api.order.command.domain.OrderCommandRepository
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
        val orderEntity = orderCommandJpaRepository.findById(id).orElseThrow()

        return OrderEntity.toDomain(orderEntity)
    }
}
