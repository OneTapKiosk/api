package com.liveforpresent.onetapkiosk.user.kiosk.command.application.handler

import com.liveforpresent.onetapkiosk.user.kiosk.command.application.command.CreateKioskCommand
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.Kiosk
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.KioskCommandRepository
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.KioskProps
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo.KioskDevice
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo.KioskStatus
import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.infrastructure.util.SnowflakeIdUtil
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
