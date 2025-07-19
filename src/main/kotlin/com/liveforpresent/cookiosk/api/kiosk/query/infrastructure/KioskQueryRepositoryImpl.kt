package com.liveforpresent.cookiosk.api.kiosk.query.infrastructure

import com.liveforpresent.cookiosk.api.kiosk.query.domain.KioskModel
import com.liveforpresent.cookiosk.api.kiosk.query.domain.KioskQueryRepository
import org.springframework.stereotype.Repository

@Repository
class KioskQueryRepositoryImpl(
    private val kioskQueryJpaRepository: KioskQueryJpaRepository
): KioskQueryRepository {
    override fun findById(id: Long): KioskModel {
        return kioskQueryJpaRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow { IllegalArgumentException("해당 키오스크가 존재 하지 않습니다.") }
    }

    override fun findByCompanyId(companyId: Long): List<KioskModel> {
        return kioskQueryJpaRepository.findByCompanyIdAndIsDeletedFalse(companyId)
    }
}
