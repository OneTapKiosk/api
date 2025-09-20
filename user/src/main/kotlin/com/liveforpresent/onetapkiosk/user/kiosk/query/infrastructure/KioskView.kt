package com.liveforpresent.onetapkiosk.user.kiosk.query.infrastructure

import com.liveforpresent.onetapkiosk.user.kiosk.query.infrastructure.converter.KioskDevicesConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

@Entity
@Immutable
@Table(name = "vw_kiosk")
class KioskView(
    @Id
    val id: Long,
    val name: String,
    val location: String,
    val status: String,
    val version: String,
    val companyId: Long,

    @Convert(converter = KioskDevicesConverter::class)
    val devices: Set<String>,
)
