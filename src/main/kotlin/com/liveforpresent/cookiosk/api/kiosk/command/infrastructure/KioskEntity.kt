package com.liveforpresent.cookiosk.api.kiosk.command.infrastructure

import com.liveforpresent.cookiosk.api.kiosk.command.domain.Kiosk
import com.liveforpresent.cookiosk.api.kiosk.command.domain.KioskProps
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.CompanyId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskDevice
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskStatus
import jakarta.persistence.*

@Entity
@Table(name = "kiosk")
class KioskEntity (
    @Id
    @Column(nullable = false)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val location: String,

    @Column(nullable = false)
    val status: String,

    @Column(nullable = false)
    val version: String,

    @ElementCollection
    @CollectionTable(name = "device", joinColumns = [JoinColumn(name = "device_id")])
    @Column(nullable = false)
    val devices: MutableSet<String>,

    @Column(nullable = false)
    val companyId: Long,
) {
    companion object {
        fun toPersistence(kiosk: Kiosk): KioskEntity {
            return KioskEntity(
                id = kiosk.id.value,
                name = kiosk.name,
                location = kiosk.location,
                status = kiosk.status.value,
                version = kiosk.version,
                companyId = kiosk.companyId.value,
                devices = kiosk.devices.map { kioskDevice -> kioskDevice.value }.toMutableSet()
            )
        }

        fun toDomain(kioskEntity: KioskEntity): Kiosk {
            val props = KioskProps(
                name = kioskEntity.name,
                location = kioskEntity.location,
                status = KioskStatus.create(kioskEntity.status),
                version = kioskEntity.version,
                devices = kioskEntity.devices.map { device -> KioskDevice.create(device) }.toMutableSet(),
                companyId = CompanyId(kioskEntity.companyId)
            )

            return Kiosk.create(KioskId(kioskEntity.id), props)
        }
    }
}
