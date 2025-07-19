package com.liveforpresent.cookiosk.api.sale.query.infrastructure

import com.liveforpresent.cookiosk.api.sale.query.domain.SaleModel
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleQueryRepository
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class SaleQueryRepositoryImpl(
    private val saleQueryJpaRepository: SaleQueryJpaRepository
): SaleQueryRepository {
    override fun findByCriteria(
        startAt: Instant?,
        endAt: Instant?,
        sortBy: String?,
        kioskId: Long
    ): List<SaleModel> {
        return saleQueryJpaRepository.findByCriteria(startAt, endAt, sortBy, kioskId)
    }
}
