package com.liveforpresent.cookiosk.api.kiosk.query.infrastructure

import com.liveforpresent.cookiosk.api.kiosk.query.domain.KioskModel
import com.liveforpresent.cookiosk.api.kiosk.query.domain.KioskQueryRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class KioskQueryRepositoryImpl(
    private val kioskQueryJpaRepository: KioskQueryJpaRepository,
    private val em: EntityManager
): KioskQueryRepository {
    override fun findById(id: Long): KioskModel {
        val kioskEntity = kioskQueryJpaRepository.findById(id)
            .orElseThrow { IllegalArgumentException("해당 키오스크가 존재 하지 않습니다.") }

        return KioskModel(
            name = kioskEntity.name,
            location = kioskEntity.location,
            status = kioskEntity.status,
            version = kioskEntity.version,
            devices = kioskEntity.devices,
            companyId = kioskEntity.companyId.toString(),
        )
    }

    override fun findByCompanyId(companyId: Long): List<KioskModel> {
        val kioskEntities = kioskQueryJpaRepository.findByCompanyId(companyId)

        return kioskEntities.map { KioskModel(
            name = it.name,
            location = it.location,
            status = it.status,
            version = it.version,
            devices = it.devices,
            companyId = it.companyId.toString()
        ) }
    }

    override fun refreshView() {
        em.createNativeQuery("REFRESH MATERIALIZED VIEW CONCURRENTLY vw_kiosk")
            .executeUpdate()
    }
}
