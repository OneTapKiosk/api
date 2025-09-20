package com.liveforpresent.onetapkiosk.user.kiosk.query.application.handler

import com.liveforpresent.onetapkiosk.user.kiosk.query.application.query.GetKioskByIdQuery
import com.liveforpresent.onetapkiosk.user.kiosk.query.domain.KioskModel
import com.liveforpresent.onetapkiosk.user.kiosk.query.domain.KioskQueryRepository
import org.springframework.stereotype.Service

@Service
class GetKioskByIdHandler(
    private val kioskQueryRepository: KioskQueryRepository
) {
    fun execute(query: GetKioskByIdQuery): KioskModel {
        return kioskQueryRepository.findById(query.kioskId)
    }
}
