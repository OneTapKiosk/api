package com.liveforpresent.cookiosk.api.kiosk.command.application.handler

import com.liveforpresent.cookiosk.api.kiosk.command.application.command.DeleteKioskCommand
import com.liveforpresent.cookiosk.api.kiosk.command.domain.KioskCommandRepository
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class DeleteKioskHandler(
    private val kioskCommandRepository: KioskCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: DeleteKioskCommand) {
        val kiosk = kioskCommandRepository.findOne(command.kioskId)
        val updatedKiosk = kiosk.delete(kiosk.id)

        kioskCommandRepository.save(updatedKiosk)

        eventPublisher.publish(updatedKiosk)
    }
}
