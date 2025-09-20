package com.liveforpresent.onetapkiosk.user.kiosk.query.application.handler

import com.liveforpresent.onetapkiosk.user.kiosk.query.application.query.GetKioskListByCompanyIdQuery
import com.liveforpresent.onetapkiosk.user.kiosk.query.domain.KioskModel
import com.liveforpresent.onetapkiosk.user.kiosk.query.domain.KioskQueryRepository
import org.springframework.stereotype.Service

@Service
class GetKioskListByCompanyIdHandler(
    private val kioskQueryRepository: KioskQueryRepository
) {
    fun execute(query: GetKioskListByCompanyIdQuery): List<KioskModel> {
        return kioskQueryRepository.findByCompanyId(query.companyId)
    }
}
