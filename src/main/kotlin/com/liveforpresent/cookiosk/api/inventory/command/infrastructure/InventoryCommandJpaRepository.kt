package com.liveforpresent.cookiosk.api.inventory.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface InventoryCommandJpaRepository: JpaRepository<InventoryEntity, Long>{
    fun findByProductId(productId: Long): Optional<InventoryEntity>
}
