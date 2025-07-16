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

@Service
class CreateInventoryHandler(
    private val inventoryCommandRepository: InventoryCommandRepository
) {
    fun execute(command: CreateInventoryCommand) {
        val inventoryProps = InventoryProps(
            isAvailable = command.isAvailable,
            quantity = command.quantity,
            productId = ProductId(command.productId),
            kioskId = KioskId(command.kioskId),
            createdAt = command.createdAt,
            updatedAt = command.updatedAt
        )

        val inventory = Inventory.create(InventoryId(SnowflakeIdUtil.generateId()), inventoryProps)

        inventoryCommandRepository.save(inventory)
    }
}
