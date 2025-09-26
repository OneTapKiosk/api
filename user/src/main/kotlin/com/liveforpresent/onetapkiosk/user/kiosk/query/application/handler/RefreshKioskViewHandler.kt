package com.liveforpresent.onetapkiosk.user.kiosk.query.application.handler

import com.liveforpresent.onetapkiosk.user.kiosk.query.domain.KioskQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class RefreshKioskViewHandler(
    private val kioskQueryRepository: KioskQueryRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute() {
        kioskQueryRepository.refreshView()
    }
}
