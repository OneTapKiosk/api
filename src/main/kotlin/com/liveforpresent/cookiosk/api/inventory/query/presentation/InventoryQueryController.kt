package com.liveforpresent.cookiosk.api.inventory.query.presentation

import com.liveforpresent.cookiosk.api.inventory.query.application.handler.GetInventoryListByCriteriaHandler
import com.liveforpresent.cookiosk.api.inventory.query.application.query.GetInventoryListByCriteriaQuery
import com.liveforpresent.cookiosk.api.inventory.query.domain.InventoryModel
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inventory")
class InventoryQueryController(
    private val getInventoryListByCriteriaHandler: GetInventoryListByCriteriaHandler
) {
    @GetMapping
    fun getInventoryListByCriteria(
        @RequestParam(required = false) isAvailable : Boolean?,
        @RequestParam(required = false) sortBy: String?,
    ): ResponseEntity<BaseApiResponse<List<InventoryModel>>> {
        val query = GetInventoryListByCriteriaQuery(isAvailable, sortBy)
        val result = getInventoryListByCriteriaHandler.execute(query)

        val response = BaseApiResponse<List<InventoryModel>>(
            success = true,
            message = "재고 목록 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("{inventoryId}")
    fun getInventoryItem(@PathVariable("inventoryId") inventoryId: String): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }
}
