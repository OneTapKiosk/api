package com.liveforpresent.cookiosk.api.kiosk.command.infrastructure

import com.liveforpresent.cookiosk.api.kiosk.command.domain.Kiosk
import com.liveforpresent.cookiosk.api.kiosk.command.domain.KioskProps
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.CompanyId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskDevice
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskStatus
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class KioskCommandRepositoryImplTest: BehaviorSpec({
    val kioskCommandJpaRepository = mockk<KioskCommandJpaRepository>()
    val kioskCommandRepositoryImpl = KioskCommandRepositoryImpl(kioskCommandJpaRepository)

    given("a kiosk") {
        val kioskId = KioskId(1L)

        val kioskProps = KioskProps(
            name = "Kiosk",
            location = "New York",
            status = KioskStatus.ONLINE,
            version = "1.0.0",
            devices = mutableSetOf(KioskDevice.CARD_READER, KioskDevice.BARCODE_READER),
            companyId = CompanyId(1L)
        )

        val kiosk = Kiosk.create(kioskId, kioskProps)

        val entityReturnedFromJpa = KioskEntity(
            id = kiosk.id.value,
            name = kiosk.name,
            location = kiosk.location,
            status = kiosk.status.value,
            version = kiosk.version,
            devices = kiosk.devices.map { device -> device.value }.toMutableSet(),
            companyId = kiosk.companyId.value,
            isDeleted = false,
            deletedAt = null
        )

        val domainReturnedFromJpa = KioskEntity.toDomain(entityReturnedFromJpa)

        every { kioskCommandJpaRepository.save(any<KioskEntity>()) } returns entityReturnedFromJpa

        `when`("kiosk를 DB에 저장한다.") {
            val result = kioskCommandRepositoryImpl.save(kiosk)

            then("kiosk 정보를 return 한다.") {
                result.id shouldBe domainReturnedFromJpa.id
                result.name shouldBe domainReturnedFromJpa.name
                result.location shouldBe domainReturnedFromJpa.location
                result.status shouldBe domainReturnedFromJpa.status
                result.version shouldBe domainReturnedFromJpa.version
                result.devices shouldBe domainReturnedFromJpa.devices
                result.companyId shouldBe domainReturnedFromJpa.companyId
            }
        }
    }
})
