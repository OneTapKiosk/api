package com.liveforpresent.cookiosk.api.inventory.command.infrastructure

import com.liveforpresent.cookiosk.api.inventory.command.domain.Inventory
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryCommandRepository
import org.springframework.stereotype.Repository

@Repository
class InventoryCommandRepositoryImpl(
    private val inventoryCommandJpaRepository: InventoryCommandJpaRepository
): InventoryCommandRepository {
    override fun save(inventory: Inventory): Inventory {
        val inventoryEntity = InventoryEntity.toPersistence(inventory)
        inventoryCommandJpaRepository.save(inventoryEntity)

        return inventory
    }

    override fun findOne(id: Long): Inventory {
        val inventoryEntity = inventoryCommandJpaRepository.findById(id)
            .orElseThrow { IllegalArgumentException("해당 재고가 존재하지 않습니다.") }

        return InventoryEntity.toDomain(inventoryEntity)
    }

    override fun findByProductId(productId: Long): Inventory {
        val inventoryEntity = inventoryCommandJpaRepository.findByProductId(productId)
            .orElseThrow { IllegalArgumentException("해당 재고가 존재하지 않습니다.") }

        return InventoryEntity.toDomain(inventoryEntity)
    }
}
