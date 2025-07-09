package com.liveforpresent.cookiosk.api.inventory.command.presentation

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
class InventoryCommandController {
    @PostMapping
    fun createInventory(@RequestBody createInventoryReqDto: Any): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PatchMapping("{inventoryId}")
    fun updateInventory(@PathVariable inventoryId: String, @RequestBody updateInventoryReqDto: Any): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("{inventoryId}")
    fun deleteInventory(@PathVariable inventoryId: String): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}