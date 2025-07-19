package com.liveforpresent.cookiosk.api.kiosk.query.application.handler

import com.liveforpresent.cookiosk.api.kiosk.query.application.query.GetKioskListByCompanyIdQuery
import com.liveforpresent.cookiosk.api.kiosk.query.domain.KioskModel
import com.liveforpresent.cookiosk.api.kiosk.query.domain.KioskQueryRepository
import org.springframework.stereotype.Service

@Service
class GetKioskListByCompanyIdHandler(
    private val kioskQueryRepository: KioskQueryRepository
) {
    fun execute(query: GetKioskListByCompanyIdQuery): List<KioskModel> {
        return kioskQueryRepository.findByCompanyId(query.companyId)
    }
}
