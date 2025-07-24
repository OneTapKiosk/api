package com.liveforpresent.cookiosk.api.inventory.command.application.handler

import com.liveforpresent.cookiosk.api.inventory.command.application.command.CreateInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.domain.Inventory
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryCommandRepository
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryProps
import com.liveforpresent.cookiosk.api.inventory.command.domain.vo.InventoryId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class CreateInventoryHandler(
    private val inventoryCommandRepository: InventoryCommandRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute(command: CreateInventoryCommand) {
        val inventoryProps = InventoryProps(
            isAvailable = true,
            quantity = command.quantity,
            productId = ProductId(command.productId),
            kioskId = KioskId(command.kioskId),
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )

        val inventory = Inventory.create(InventoryId(SnowflakeIdUtil.generateId()), inventoryProps)

        inventoryCommandRepository.save(inventory)
    }
}
