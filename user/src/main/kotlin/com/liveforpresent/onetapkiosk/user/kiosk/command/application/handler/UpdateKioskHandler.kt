package com.liveforpresent.onetapkiosk.user.kiosk.command.application.handler

import com.liveforpresent.onetapkiosk.user.kiosk.command.application.command.UpdateKioskCommand
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.KioskCommandRepository
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo.KioskDevice
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo.KioskStatus
import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UpdateKioskHandler(
    private val kioskCommandRepository: KioskCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: UpdateKioskCommand) {
        val kiosk = kioskCommandRepository.findOne(command.kioskId)

        val updatedKiosk = kiosk.update(
            newName = command.name ?: kiosk.name,
            newLocation = command.location ?: kiosk.location,
            newStatus = command.status?.let { KioskStatus.create(it) } ?: kiosk.status,
            newVersion = command.version ?: kiosk.version,
            newDevices = command.devices?.map { KioskDevice.create(it) }?.toMutableSet() ?: kiosk.devices,
            newCompanyId = command.companyId?.let { CompanyId(it) } ?: kiosk.companyId,
        )

        kioskCommandRepository.save(updatedKiosk)

        eventPublisher.publish(updatedKiosk)
    }
}
