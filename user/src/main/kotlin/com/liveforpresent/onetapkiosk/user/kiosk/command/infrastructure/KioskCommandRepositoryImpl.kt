package com.liveforpresent.onetapkiosk.user.kiosk.command.infrastructure

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.Kiosk
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.KioskCommandRepository
import com.liveforpresent.onetapkiosk.user.shared.exception.KioskExceptionCode
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
                KioskExceptionCode.KIOSK_NOT_FOUND,
                "[KioskCommandRepository] KioskId: ${id}에 해당하는 키오스크가 존재하지 않습니다."
            )
        }

        return KioskEntity.toDomain(kioskEntity)
    }
}
