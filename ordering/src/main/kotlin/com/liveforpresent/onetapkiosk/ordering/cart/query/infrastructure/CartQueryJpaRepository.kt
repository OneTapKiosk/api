package com.liveforpresent.onetapkiosk.ordering.cart.query.infrastructure

import com.liveforpresent.onetapkiosk.ordering.cart.command.infrastructure.CartEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CartQueryJpaRepository: JpaRepository<CartEntity, Long> {
    fun findCartById(id: Long): Optional<CartEntity>
}
