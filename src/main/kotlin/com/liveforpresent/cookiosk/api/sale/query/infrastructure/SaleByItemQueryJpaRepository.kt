package com.liveforpresent.cookiosk.api.sale.query.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface SaleByItemQueryJpaRepository: JpaRepository<SaleByItemView, String> {
    @Query("""
        SELECT si.name name, SUM(si.price * si.quantity) totalPrice, SUM(si.quantity) totalQuantity
        FROM SaleEntity s
        JOIN s.saleItems si
        WHERE (:startAt IS NULL OR s.createdAt >= :startAt)
        AND (:endAt IS NULL OR s.createdAt <= :endAt)
        GROUP BY si.name
        ORDER BY totalPrice DESC
    """)
    fun findByItem(
        @Param("startAt") startAt: Instant?,
        @Param("endAt") endAt: Instant?,): List<SaleByItemView>
}
