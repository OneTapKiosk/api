package com.liveforpresent.cookiosk.api.kiosk.command.presentation

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
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
@RequestMapping("/kiosk")
class KioskCommandController {
    @PostMapping
    fun createKiosk(): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PatchMapping("{kioskId}")
    fun updateKiosk(@PathVariable kioskId: String, @RequestBody updateKioskReqDto: Any): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("{kioskId}")
    fun deleteKiosk(@PathVariable kioskId: String): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
