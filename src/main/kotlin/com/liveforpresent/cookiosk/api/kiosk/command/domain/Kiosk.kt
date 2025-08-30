package com.liveforpresent.cookiosk.api.kiosk.command.domain

import com.liveforpresent.cookiosk.api.kiosk.command.domain.event.KioskCreatedEvent
import com.liveforpresent.cookiosk.api.kiosk.command.domain.event.KioskDeletedEvent
import com.liveforpresent.cookiosk.api.kiosk.command.domain.event.KioskUpdatedEvent
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.CompanyId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskDevice
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskStatus
import com.liveforpresent.cookiosk.shared.core.domain.AggregateRoot
import java.time.Instant

class Kiosk private constructor(
    id: KioskId,
    private val props: KioskProps,
) : AggregateRoot<KioskId>(id) {
    companion object {
        fun create(id: KioskId, props: KioskProps): Kiosk {
            val kiosk = Kiosk(id, props)
            kiosk.validate()

            kiosk.addDomainEvent(KioskCreatedEvent())

            return kiosk
        }
    }

    fun validate() {
        require(props.name.isNotBlank()) { "[Kiosk] 이름은 필수 입니다." }
        require(props.name.length < 32) { "[Kiosk] 이름은 최대 32자 입니다." }
    }

    fun update(
        newName: String = this.name,
        newLocation: String = this.location,
        newStatus: KioskStatus = this.status,
        newVersion: String = this.version,
        newDevices: Set<KioskDevice> = this.devices,
        newCompanyId: CompanyId = this.companyId,
    ): Kiosk {
        val updatedKiosk = Kiosk(
            id, props.copy(
                name = newName,
                location = newLocation,
                status = newStatus,
                version = newVersion,
                devices = newDevices.toMutableSet(),
                companyId = newCompanyId,
            )
        )

        updatedKiosk.validate()

        updatedKiosk.addDomainEvent(KioskUpdatedEvent())

        return updatedKiosk
    }

    fun delete(id: KioskId): Kiosk {
        val updatedKiosk = Kiosk(
            id, props.copy(
                isDeleted = true,
                deletedAt = Instant.now(),
            )
        )

        updatedKiosk.addDomainEvent(KioskDeletedEvent())

        return updatedKiosk
    }

    val name: String get() = props.name
    val location: String get() = props.location
    val status: KioskStatus get() = props.status
    val version: String get() = props.version
    val devices: MutableSet<KioskDevice> get() = props.devices
    val companyId: CompanyId get() = props.companyId
    val isDeleted: Boolean get() = props.isDeleted
    val deletedAt: Instant? get() = props.deletedAt
}
