package com.liveforpresent.cookiosk.api.inventory.query.infrastructure

import com.liveforpresent.cookiosk.api.inventory.query.domain.InventoryModel
import com.liveforpresent.cookiosk.api.inventory.query.domain.InventoryQueryRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class InventoryQueryRepositoryImpl(
    private val inventoryQueryJpaRepository: InventoryQueryJpaRepository,
    private val em: EntityManager
): InventoryQueryRepository {
    override fun findByCriteria(
        isAvailable: Boolean?,
        sortBy: String?
    ): List<InventoryModel> {
        val inventoryViews = inventoryQueryJpaRepository.findByCriteria(isAvailable, sortBy)

        return inventoryViews.map {
            InventoryModel(
                id = it.id.toString(),
                isAvailable = it.isAvailable,
                quantity = it.quantity,
                productId = it.productId.toString(),
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    override fun refreshView() {
        em.createNativeQuery("REFRESH MATERIALIZED VIEW CONCURRENTLY vw_inventory")
            .executeUpdate()
    }
}
