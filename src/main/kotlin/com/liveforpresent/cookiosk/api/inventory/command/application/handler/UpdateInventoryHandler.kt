package com.liveforpresent.cookiosk.api.inventory.command.application.handler

import com.liveforpresent.cookiosk.api.inventory.command.application.command.UpdateInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryCommandRepository
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UpdateInventoryHandler(
    private val inventoryCommandRepository: InventoryCommandRepository,
) {
    @Transactional
    fun execute(command: UpdateInventoryCommand) {
        val inventory = inventoryCommandRepository.findOne(command.inventoryId)

        val updatedInventory = inventory.updateInventory(
            newIsAvailable = command.isAvailable ?: inventory.isAvailable,
            newQuantity = command.quantity ?: inventory.quantity,
            newProductId = command.productId?.let { ProductId(command.productId) } ?: inventory.productId
        )

        inventoryCommandRepository.save(updatedInventory)
    }
}
