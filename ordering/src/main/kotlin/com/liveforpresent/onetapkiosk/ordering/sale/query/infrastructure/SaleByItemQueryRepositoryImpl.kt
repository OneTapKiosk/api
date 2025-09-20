package com.liveforpresent.onetapkiosk.ordering.sale.query.infrastructure

import com.liveforpresent.onetapkiosk.ordering.sale.query.domain.SaleByItemModel
import com.liveforpresent.onetapkiosk.ordering.sale.query.domain.SaleByItemQueryRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class SaleByItemQueryRepositoryImpl(
    private val saleByItemQueryJpaRepository: SaleByItemQueryJpaRepository,
    private val em: EntityManager
): SaleByItemQueryRepository {
    override fun findByItem(startAt: Instant?, endAt: Instant?, kioskId: Long): List<SaleByItemModel> {
        val saleByItemViews = saleByItemQueryJpaRepository.findAll()

        return saleByItemViews.map {
            SaleByItemModel(
                name = it.name,
                totalQuantity = it.totalQuantity,
                totalPrice = it.totalPrice
            )
        }
    }

    override fun refreshView() {
        em.createNativeQuery("REFRESH MATERIALIZED VIEW CONCURRENTLY vw_sale_by_item")
            .executeUpdate()
    }
}
