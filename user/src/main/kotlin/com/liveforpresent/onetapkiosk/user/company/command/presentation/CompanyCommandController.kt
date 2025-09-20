package com.liveforpresent.onetapkiosk.user.company.command.presentation

import com.liveforpresent.onetapkiosk.user.company.command.application.command.CreateCompanyCommand
import com.liveforpresent.onetapkiosk.user.company.command.application.command.DeleteCompanyCommand
import com.liveforpresent.onetapkiosk.user.company.command.application.command.UpdateCompanyCommand
import com.liveforpresent.onetapkiosk.user.company.command.application.dto.response.CreateCompanyResDto
import com.liveforpresent.onetapkiosk.user.company.command.application.handler.CreateCompanyHandler
import com.liveforpresent.onetapkiosk.user.company.command.application.handler.DeleteCompanyHandler
import com.liveforpresent.onetapkiosk.common.core.presentation.BaseApiResponse
import com.liveforpresent.onetapkiosk.user.company.command.application.handler.UpdateCompanyHandler
import com.liveforpresent.onetapkiosk.user.company.command.presentation.dto.request.CreateCompanyReqDto
import com.liveforpresent.onetapkiosk.user.company.command.presentation.dto.request.DeleteCompanyReqDto
import com.liveforpresent.onetapkiosk.user.company.command.presentation.dto.request.UpdateCompanyReqDto
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
@RequestMapping("/company")
class CompanyCommandController(
    private val createCompanyHandler: CreateCompanyHandler,
    private val updateCompanyHandler: UpdateCompanyHandler,
    private val deleteCompanyHandler: DeleteCompanyHandler
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

    @PatchMapping("/{id}")
    fun updateCompany(@PathVariable id: String, @RequestBody dto: UpdateCompanyReqDto): ResponseEntity<BaseApiResponse<Unit>> {
        val command = UpdateCompanyCommand(
            id = id.toLong(),
            newRegistrationNumber = dto.registrationNumber,
            newPhone = dto.phone,
            newEmail = dto.email
        )

        updateCompanyHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "Company 정보 수정 성공"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @DeleteMapping("/{id}")
    fun deleteCompany(@PathVariable id: String, @RequestBody dto: DeleteCompanyReqDto): ResponseEntity<BaseApiResponse<Unit>> {
        val command = DeleteCompanyCommand(
            id = id.toLong(),
            registrationNumber =  dto.registrationNumber
        )

        deleteCompanyHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "Company 삭제 성공",
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
