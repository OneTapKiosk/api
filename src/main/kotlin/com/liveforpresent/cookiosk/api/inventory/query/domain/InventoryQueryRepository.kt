package com.liveforpresent.cookiosk.api.inventory.query.domain

interface InventoryQueryRepository {
    fun findByCriteria(
        isAvailable: Boolean?,
        sortBy: String?
    ): List<InventoryModel>
}
