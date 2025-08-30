package com.liveforpresent.cookiosk.api.sale.query.infrastructure

import com.liveforpresent.cookiosk.api.sale.query.domain.SaleModel
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleQueryRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class SaleQueryRepositoryImpl(
    private val saleQueryJpaRepository: SaleQueryJpaRepository,
    private val em: EntityManager
): SaleQueryRepository {
    override fun findByCriteria(
        startAt: Instant?,
        endAt: Instant?,
        sortBy: String?,
        kioskId: Long
    ): List<SaleModel> {
        val saleEntities = saleQueryJpaRepository.findByCriteria(startAt, endAt, sortBy, kioskId)

        return saleEntities.map { SaleModel(
            createdAt = it.createdAt,
            totalPrice = it.totalPrice,
            kioskId = it.kioskId.toString()
        ) }
    }

    override fun refreshView() {
        em.createNativeQuery("REFRESH MATERIALIZED VIEW CONCURRENTLY vw_sale")
            .executeUpdate()
    }
/*
    override fun findSummaryByItem(startAt: Instant?, endAt: Instant?, kioskId: Long): List<SaleByItemModel> {
        return saleQueryJpaRepository.findSummaryByItem(startAt, endAt, kioskId)
    }
*/
}
