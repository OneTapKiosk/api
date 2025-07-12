package com.liveforpresent.cookiosk.api.cart.command.infrastructure

import com.liveforpresent.cookiosk.api.cart.command.infrastructure.entity.CartEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CartCommandJpaRepository: JpaRepository<CartEntity, Long>
