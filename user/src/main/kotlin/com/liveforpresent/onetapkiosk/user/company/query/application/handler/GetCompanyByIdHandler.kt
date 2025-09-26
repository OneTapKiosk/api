package com.liveforpresent.onetapkiosk.user.company.query.application.handler

import com.liveforpresent.onetapkiosk.user.company.query.application.query.GetCompanyByIdQuery
import com.liveforpresent.onetapkiosk.user.company.query.domain.CompanyModel
import com.liveforpresent.onetapkiosk.user.company.query.domain.CompanyQueryRepository
import org.springframework.stereotype.Service

@Service
class GetCompanyByIdHandler(
    private val companyQueryRepository: CompanyQueryRepository
) {
    fun execute(query: GetCompanyByIdQuery): CompanyModel {
        return companyQueryRepository.findById(query.id)
    }
}
