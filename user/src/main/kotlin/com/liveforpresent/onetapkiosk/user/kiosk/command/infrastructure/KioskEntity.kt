package com.liveforpresent.onetapkiosk.user.kiosk.command.infrastructure

import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.Kiosk
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.KioskProps
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo.KioskDevice
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.vo.KioskStatus
import jakarta.persistence.*
import java.time.Instant

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

    @Column(nullable = false)
    val isDeleted: Boolean,

    @Column(nullable = true)
    val deletedAt: Instant?,
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
                devices = kiosk.devices.map { kioskDevice -> kioskDevice.value }.toMutableSet(),
                isDeleted = kiosk.isDeleted,
                deletedAt = kiosk.deletedAt
            )
        }

        fun toDomain(kioskEntity: KioskEntity): Kiosk {
            val props = KioskProps(
                name = kioskEntity.name,
                location = kioskEntity.location,
                status = KioskStatus.create(kioskEntity.status),
                version = kioskEntity.version,
                devices = kioskEntity.devices.map { device -> KioskDevice.create(device) }.toMutableSet(),
                companyId = CompanyId(kioskEntity.companyId),
                isDeleted = kioskEntity.isDeleted,
                deletedAt = kioskEntity.deletedAt,
            )

            return Kiosk.create(KioskId(kioskEntity.id), props)
        }
    }
}
