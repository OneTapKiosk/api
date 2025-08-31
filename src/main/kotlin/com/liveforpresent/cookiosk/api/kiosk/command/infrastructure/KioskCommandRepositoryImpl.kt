package com.liveforpresent.cookiosk.api.kiosk.command.infrastructure

import com.liveforpresent.cookiosk.api.kiosk.command.domain.Kiosk
import com.liveforpresent.cookiosk.api.kiosk.command.domain.KioskCommandRepository
import com.liveforpresent.cookiosk.shared.exception.CustomException
import com.liveforpresent.cookiosk.shared.exception.CustomExceptionCode
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

    override fun findOne(id: Long): Kiosk {
        val kioskEntity = kioskCommandJpaRepository.findById(id).orElseThrow {
            throw CustomException(
                CustomExceptionCode.KIOSK_NOT_FOUND,
                "[KioskCommandRepository] KioskId: ${id}에 해당하는 키오스크가 존재하지 않습니다."
            )
        }

        return KioskEntity.toDomain(kioskEntity)
    }
}
