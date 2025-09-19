package com.liveforpresent.cookiosk.api.company.query.presentation

import com.liveforpresent.cookiosk.api.company.query.application.handler.GetCompanyByIdHandler
import com.liveforpresent.cookiosk.api.company.query.application.handler.GetCompanyListHandler
import com.liveforpresent.cookiosk.api.company.query.application.query.GetCompanyByIdQuery
import com.liveforpresent.cookiosk.api.company.query.domain.CompanyModel
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/company")
class CompanyQueryController(
    private val getCompanyByIdHandler: GetCompanyByIdHandler,
    private val getCompanyListHandler: GetCompanyListHandler
) {
    @GetMapping("/{id}")
    fun getCompanyById(@PathVariable("id") id: String): ResponseEntity<BaseApiResponse<CompanyModel>> {
        val query = GetCompanyByIdQuery(id.toLong())
        val result = getCompanyByIdHandler.execute(query)

        val response = BaseApiResponse<CompanyModel>(
            success = true,
            message = "Company 조회 성공",
            data = result,
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping
    fun getCompanyList(): ResponseEntity<BaseApiResponse<List<CompanyModel>>> {
        val result = getCompanyListHandler.execute()

        val response = BaseApiResponse<List<CompanyModel>>(
            success = true,
            message = "Company 목록 조회 성공",
            data = result,
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
