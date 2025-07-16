package com.liveforpresent.cookiosk.api.inventory.command.application.handler

import com.liveforpresent.cookiosk.api.inventory.command.application.command.DeleteInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryCommandRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class DeleteInventoryHandler(
    private val inventoryCommandRepository: InventoryCommandRepository
) {
    @Transactional
    fun execute(command: DeleteInventoryCommand) {
        val inventory = inventoryCommandRepository.findOne(command.inventoryId)
        val updatedInventory = inventory.delete(inventory.id)

        inventoryCommandRepository.save(updatedInventory)
    }
}
