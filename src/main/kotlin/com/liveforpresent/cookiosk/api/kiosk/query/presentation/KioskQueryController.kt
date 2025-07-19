package com.liveforpresent.cookiosk.api.kiosk.query.presentation

import com.liveforpresent.cookiosk.api.kiosk.query.application.handler.GetKioskByIdHandler
import com.liveforpresent.cookiosk.api.kiosk.query.application.query.GetKioskByIdQuery
import com.liveforpresent.cookiosk.api.kiosk.query.domain.KioskModel
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kiosk")
class KioskQueryController(
    private val getKioskByIdHandler: GetKioskByIdHandler
) {
    @GetMapping
    fun getKioskList(): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("{kioskId}")
    fun getKioskDetail(@PathVariable("kioskId") id: Long): ResponseEntity<BaseApiResponse<KioskModel>> {
        val query = GetKioskByIdQuery(id)
        val result = getKioskByIdHandler.execute(query)

        val response = BaseApiResponse<KioskModel>(
            success = true,
            message = "키오스크 정보 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
