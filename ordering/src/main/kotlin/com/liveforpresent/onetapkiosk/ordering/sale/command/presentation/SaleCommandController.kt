package com.liveforpresent.onetapkiosk.ordering.sale.command.presentation

import com.liveforpresent.onetapkiosk.common.core.presentation.BaseApiResponse
import com.liveforpresent.onetapkiosk.ordering.sale.command.application.handler.CreateSaleHandler
import com.liveforpresent.onetapkiosk.ordering.sale.command.presentation.dto.request.CreateSaleReqDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sale")
class SaleCommandController(
    private val createSaleHandler: CreateSaleHandler
) {
    @PostMapping
    fun createSale(@RequestBody reqDto: CreateSaleReqDto): ResponseEntity<BaseApiResponse<Unit>> {
        val command = reqDto.toCommand()
        createSaleHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "매출 정보 생성 성공",
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}
