package com.liveforpresent.cookiosk.api.company.query.application.handler

import com.liveforpresent.cookiosk.api.company.query.domain.CompanyQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class RefreshCompanyViewHandler(
    private val companyQueryRepository: CompanyQueryRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute() {
        companyQueryRepository.refreshView()
    }
}
