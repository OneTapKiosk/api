package com.liveforpresent.cookiosk.api.kiosk.command.infrastructure

import com.liveforpresent.cookiosk.api.kiosk.command.domain.Kiosk
import com.liveforpresent.cookiosk.api.kiosk.command.domain.KioskCommandRepository
import org.springframework.stereotype.Repository

@Repository
class KioskCommandRepositoryImpl(
    private val kioskCommandJpaRepository: KioskCommandJpaRepository
): KioskCommandRepository {
    override fun save(kiosk: Kiosk): Kiosk {
        val kioskEntity = KioskEntity.toPersistence(kiosk)
        kioskCommandJpaRepository.save(kioskEntity)

        return kiosk
    }
}
