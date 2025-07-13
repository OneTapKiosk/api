package com.liveforpresent.cookiosk.api.inventory.command.domain

interface InventoryCommandRepository {
    fun save(inventory: Inventory): Inventory
}
