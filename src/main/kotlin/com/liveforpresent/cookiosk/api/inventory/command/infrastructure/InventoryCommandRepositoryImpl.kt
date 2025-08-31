package com.liveforpresent.cookiosk.api.inventory.command.infrastructure

import com.liveforpresent.cookiosk.api.inventory.command.domain.Inventory
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryCommandRepository
import com.liveforpresent.cookiosk.shared.exception.CustomException
import com.liveforpresent.cookiosk.shared.exception.CustomExceptionCode
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
            .orElseThrow {
                CustomException(
                    CustomExceptionCode.INVENTORY_NOT_FOUND,
                    "[InventoryCommandRepository] InventoryId: ${id}에 해당하는 재고가 존재하지 않습니다."
                )
            }

        return InventoryEntity.toDomain(inventoryEntity)
    }

    override fun findByProductId(productId: Long): Inventory {
        val inventoryEntity = inventoryCommandJpaRepository.findByProductId(productId)
            .orElseThrow {
                CustomException(
                    CustomExceptionCode.INVENTORY_NOT_FOUND,
                    "[InventoryCommandRepository] ProductId: ${productId}에 해당하는 재고가 존재하지 않습니다."
                )
            }

        return InventoryEntity.toDomain(inventoryEntity)
    }
}
