package com.liveforpresent.cookiosk.api.company.command.presentation

import com.liveforpresent.cookiosk.api.company.command.application.command.CreateCompanyCommand
import com.liveforpresent.cookiosk.api.company.command.application.dto.response.CreateCompanyResDto
import com.liveforpresent.cookiosk.api.company.command.application.handler.CreateCompanyHandler
import com.liveforpresent.cookiosk.api.company.command.presentation.dto.request.CreateCompanyReqDto
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/company")
class CompanyCommandController(
    private val createCompanyHandler: CreateCompanyHandler
) {
    @PostMapping
    fun createCompany(@RequestBody dto: CreateCompanyReqDto): ResponseEntity<BaseApiResponse<CreateCompanyResDto>> {
        val command = CreateCompanyCommand(
            registrationNumber = dto.registrationNumber,
            phone = dto.phone,
            email = dto.email
        )

        val result = createCompanyHandler.execute(command)

        val response = BaseApiResponse<CreateCompanyResDto>(
            success = true,
            message = "Company 생성 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}
