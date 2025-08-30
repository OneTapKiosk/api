package com.liveforpresent.cookiosk.api.inventory.command.application.handler

import com.liveforpresent.cookiosk.api.inventory.command.application.command.DeleteInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryCommandRepository
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteInventoryHandler(
    private val inventoryCommandRepository: InventoryCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute(command: DeleteInventoryCommand) {
        val inventory = inventoryCommandRepository.findByProductId(command.productId)
        val updatedInventory = inventory.delete(inventory.id)

        inventoryCommandRepository.save(updatedInventory)

        eventPublisher.publish(updatedInventory)
    }
}
