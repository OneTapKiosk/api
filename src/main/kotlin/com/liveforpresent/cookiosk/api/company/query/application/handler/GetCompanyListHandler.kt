package com.liveforpresent.cookiosk.api.company.query.application.handler

import com.liveforpresent.cookiosk.api.company.query.domain.CompanyModel
import com.liveforpresent.cookiosk.api.company.query.domain.CompanyQueryRepository
import org.springframework.stereotype.Service

@Service
class GetCompanyListHandler(
    private val companyQueryRepository: CompanyQueryRepository
) {
    fun execute() : List<CompanyModel> {
        return companyQueryRepository.findAll()
    }
}
