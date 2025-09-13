package com.liveforpresent.cookiosk.api.inventory.command.application.handler

import com.liveforpresent.cookiosk.api.inventory.command.application.command.DecreaseInventoryQuantityCommand
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryCommandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class DecreaseInventoryQuantityHandler(
    private val inventoryCommandRepository: InventoryCommandRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute(command: DecreaseInventoryQuantityCommand) {
        val inventory = inventoryCommandRepository.findByProductId(command.productId)
        val updatedInventory = inventory.decreaseQuantity(command.amount)

        inventoryCommandRepository.save(updatedInventory)
    }
}
