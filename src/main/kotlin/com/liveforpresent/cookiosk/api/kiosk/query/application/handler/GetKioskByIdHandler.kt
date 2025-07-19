package com.liveforpresent.cookiosk.api.kiosk.query.application.handler

import com.liveforpresent.cookiosk.api.kiosk.query.application.query.GetKioskByIdQuery
import com.liveforpresent.cookiosk.api.kiosk.query.domain.KioskModel
import com.liveforpresent.cookiosk.api.kiosk.query.domain.KioskQueryRepository
import org.springframework.stereotype.Service

@Service
class GetKioskByIdHandler(
    private val kioskQueryRepository: KioskQueryRepository
) {
    fun execute(query: GetKioskByIdQuery): KioskModel {
        return kioskQueryRepository.findById(query.kioskId)
    }
}
