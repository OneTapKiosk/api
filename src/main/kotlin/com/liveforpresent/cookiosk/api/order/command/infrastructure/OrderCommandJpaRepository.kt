package com.liveforpresent.cookiosk.api.order.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface OrderCommandJpaRepository: JpaRepository<OrderEntity, Long>
