package com.liveforpresent.cookiosk.api.inventory.query.application.handler

import com.liveforpresent.cookiosk.api.inventory.query.application.query.GetInventoryListByCriteriaQuery
import com.liveforpresent.cookiosk.api.inventory.query.domain.InventoryModel
import com.liveforpresent.cookiosk.api.inventory.query.domain.InventoryQueryRepository
import org.springframework.stereotype.Service

@Service
class GetInventoryListByCriteriaHandler(
    private val inventoryQueryRepository: InventoryQueryRepository
) {
    fun execute(query: GetInventoryListByCriteriaQuery): List<InventoryModel> {
        return inventoryQueryRepository.findByCriteria(query.isAvailable, query.sortBy)
    }
}
