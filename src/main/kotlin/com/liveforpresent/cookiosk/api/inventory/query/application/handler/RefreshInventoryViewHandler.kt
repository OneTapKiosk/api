package com.liveforpresent.cookiosk.api.inventory.query.application.handler

import com.liveforpresent.cookiosk.api.inventory.query.domain.InventoryQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class RefreshInventoryViewHandler(
    private val inventoryQueryRepository: InventoryQueryRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute() {
        inventoryQueryRepository.refreshView()
    }
}
