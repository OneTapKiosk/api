package com.liveforpresent.cookiosk.api.inventory.command.presentation

import com.liveforpresent.cookiosk.api.inventory.command.application.command.DeleteInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.application.command.UpdateInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.application.handler.DeleteInventoryHandler
import com.liveforpresent.cookiosk.api.inventory.command.application.handler.UpdateInventoryHandler
import com.liveforpresent.cookiosk.api.inventory.command.presentation.dto.request.UpdateInventoryReqDto
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inventory")
class InventoryCommandController(
    private val updateInventoryHandler: UpdateInventoryHandler,
    private val deleteInventoryHandler: DeleteInventoryHandler
) {
    @PostMapping
    fun createInventory(@RequestBody createInventoryReqDto: Any): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PatchMapping("{inventoryId}")
    fun updateInventory(@PathVariable inventoryId: Long, @RequestBody updateInventoryReqDto: UpdateInventoryReqDto): ResponseEntity<BaseApiResponse<Unit>> {
        val command = UpdateInventoryCommand(
            inventoryId = inventoryId,
            isAvailable = updateInventoryReqDto.isAvailable,
            quantity = updateInventoryReqDto.quantity,
            productId = updateInventoryReqDto.productId,
        )

        updateInventoryHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "재고 정보 수정 성공"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @DeleteMapping("{inventoryId}")
    fun deleteInventory(@PathVariable inventoryId: Long): ResponseEntity<BaseApiResponse<Unit>> {
        val command = DeleteInventoryCommand(inventoryId)

        deleteInventoryHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "재고 삭제 성공"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
