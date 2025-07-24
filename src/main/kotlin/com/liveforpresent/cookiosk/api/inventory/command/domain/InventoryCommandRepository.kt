package com.liveforpresent.cookiosk.api.inventory.command.domain

interface InventoryCommandRepository {
    fun save(inventory: Inventory): Inventory
    fun findOne(id: Long): Inventory
    fun findByProductId(productId: Long): Inventory
}
