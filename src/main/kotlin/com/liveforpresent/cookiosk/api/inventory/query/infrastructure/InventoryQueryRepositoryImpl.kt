package com.liveforpresent.cookiosk.api.inventory.query.infrastructure

import com.liveforpresent.cookiosk.api.inventory.query.domain.InventoryModel
import com.liveforpresent.cookiosk.api.inventory.query.domain.InventoryQueryRepository
import org.springframework.stereotype.Repository

@Repository
class InventoryQueryRepositoryImpl(
    private val inventoryQueryJpaRepository: InventoryQueryJpaRepository
): InventoryQueryRepository {
    override fun findByCriteria(
        isAvailable: Boolean?,
        sortBy: String?
    ): List<InventoryModel> {
        return inventoryQueryJpaRepository.findByCriteria(isAvailable, sortBy)
    }
}
