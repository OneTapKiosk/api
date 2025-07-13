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
}
