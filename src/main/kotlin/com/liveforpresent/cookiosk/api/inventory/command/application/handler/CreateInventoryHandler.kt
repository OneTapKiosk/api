package com.liveforpresent.cookiosk.api.inventory.command.application.handler

import com.liveforpresent.cookiosk.api.inventory.command.application.command.CreateInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.domain.Inventory
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryCommandRepository
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryProps
import com.liveforpresent.cookiosk.api.inventory.command.domain.vo.InventoryId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.product.command.domain.event.ProductCreatedEvent
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import jakarta.transaction.Transactional
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CreateInventoryHandler(
    private val inventoryCommandRepository: InventoryCommandRepository
) {
    @EventListener
    @Transactional
    fun execute(event: ProductCreatedEvent) {
        val inventoryProps = InventoryProps(
            isAvailable = true,
            quantity = 0,
            productId = event.productId,
            kioskId = event.kioskId,
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )

        val inventory = Inventory.create(InventoryId(SnowflakeIdUtil.generateId()), inventoryProps)

        inventoryCommandRepository.save(inventory)
    }
}
