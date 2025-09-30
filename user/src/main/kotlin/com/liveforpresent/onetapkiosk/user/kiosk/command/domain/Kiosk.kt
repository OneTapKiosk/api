package com.liveforpresent.onetapkiosk.user.kiosk.command.domain

import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.event.KioskCreatedEvent
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.event.KioskDeletedEvent
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.event.KioskUpdatedEvent
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo.KioskDevice
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo.KioskStatus
import com.liveforpresent.onetapkiosk.common.core.domain.AggregateRoot
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.user.shared.exception.KioskExceptionCode
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
        require(props.name.isNotBlank()) {
            throw CustomException(
                KioskExceptionCode.KIOSK_NAME_EMPTY,
                "[Kiosk] 키오스크명은 필수 입니다."
            )
        }
        require(props.name.length < 32) {
            throw CustomException(
                KioskExceptionCode.KIOSK_NAME_LENGTH_EXCEEDED,
                "[Kiosk] 키오스크명은 최대 31자 입니다."
            )
        }
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

        updatedKiosk.addDomainEvent(KioskDeletedEvent(id.value))

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
