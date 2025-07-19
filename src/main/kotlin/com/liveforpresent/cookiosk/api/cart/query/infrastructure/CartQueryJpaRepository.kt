package com.liveforpresent.cookiosk.api.cart.query.infrastructure

import com.liveforpresent.cookiosk.api.cart.command.infrastructure.CartEntity
import com.liveforpresent.cookiosk.api.cart.query.domain.CartModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CartQueryJpaRepository: JpaRepository<CartEntity, Long> {
    fun findCartById(id: Long): Optional<CartEntity>
}
