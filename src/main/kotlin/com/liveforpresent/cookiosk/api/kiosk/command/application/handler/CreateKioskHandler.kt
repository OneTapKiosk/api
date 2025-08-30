package com.liveforpresent.cookiosk.api.kiosk.command.application.handler

import com.liveforpresent.cookiosk.api.kiosk.command.application.command.CreateKioskCommand
import com.liveforpresent.cookiosk.api.kiosk.command.domain.Kiosk
import com.liveforpresent.cookiosk.api.kiosk.command.domain.KioskCommandRepository
import com.liveforpresent.cookiosk.api.kiosk.command.domain.KioskProps
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.CompanyId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskDevice
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskStatus
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateKioskHandler(
    private val kioskCommandRepository: KioskCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: CreateKioskCommand) {
        val kioskId = KioskId(SnowflakeIdUtil.generateId())

        val kioskProps = KioskProps(
            name = command.name,
            location = command.location,
            status = KioskStatus.create(command.status),
            version = command.version,
            devices = command.devices.map { device -> KioskDevice.create(device) }.toMutableSet(),
            companyId = CompanyId(command.companyId),
        )

        val kiosk = Kiosk.create(kioskId, kioskProps)

        kioskCommandRepository.save(kiosk)

        eventPublisher.publish(kiosk)
    }
}
