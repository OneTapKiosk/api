package com.liveforpresent.onetapkiosk.user.kiosk.query.presentation

import com.liveforpresent.onetapkiosk.common.core.presentation.BaseApiResponse
import com.liveforpresent.onetapkiosk.user.kiosk.query.application.handler.GetKioskByIdHandler
import com.liveforpresent.onetapkiosk.user.kiosk.query.application.handler.GetKioskListByCompanyIdHandler
import com.liveforpresent.onetapkiosk.user.kiosk.query.application.query.GetKioskByIdQuery
import com.liveforpresent.onetapkiosk.user.kiosk.query.application.query.GetKioskListByCompanyIdQuery
import com.liveforpresent.onetapkiosk.user.kiosk.query.domain.KioskModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kiosk")
class KioskQueryController(
    private val getKioskListByCompanyIdHandler: GetKioskListByCompanyIdHandler,
    private val getKioskByIdHandler: GetKioskByIdHandler
) {
    @GetMapping
    fun getKioskList(@RequestParam("companyId") companyId: Long): ResponseEntity<BaseApiResponse<List<KioskModel>>> {
        val query = GetKioskListByCompanyIdQuery(companyId)
        val result = getKioskListByCompanyIdHandler.execute(query)

        val response = BaseApiResponse<List<KioskModel>>(
            success = true,
            message = "키오스크 목록 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
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
