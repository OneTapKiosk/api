package com.liveforpresent.cookiosk.api.sale.query.infrastructure

import com.liveforpresent.cookiosk.api.sale.command.infrastructure.SaleEntity
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleByItemModel
import com.liveforpresent.cookiosk.api.sale.query.domain.SaleModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.Instant

interface SaleQueryJpaRepository: JpaRepository<SaleEntity, Long> {
    @Query("""
        SELECT s FROM SaleEntity s
        WHERE (:startAt IS NULL OR s.createdAt >= :startAt)
        AND s.kioskId = :kioskId
        AND (:endAt IS NULL OR s.createdAt <= :endAt)
        ORDER BY
            CASE WHEN :sortBy = 'CREATED_AT_ASC' THEN s.createdAt END ASC,
            CASE WHEN :sortBy = 'CREATED_AT_DESC' THEN s.createdAt END DESC,
            CASE WHEN :sortBy = 'TOTAL_PRICE_ASC' THEN s.totalPrice END ASC,
            CASE WHEN :sortBy = 'TOTAL_PRICE_DESC' THEN s.createdAt END DESC,
            s.createdAt DESC
    """)
    fun findByCriteria(
        @Param("startAt") startAt: Instant?,
        @Param("endAt") endAt: Instant?,
        @Param("sortBy") sortBy: String?,
        @Param("kioskId") kioskId: Long
    ): List<SaleModel>

    @Query("""
        SELECT si.name name, SUM(si.price * si.quantity) totalPrice, SUM(si.quantity) totalQuantity
        FROM SaleEntity s
        JOIN s.saleItems si
        WHERE (:startAt IS NULL OR s.createdAt >= :startAt)
        AND (:endAt IS NULL OR s.createdAt <= :endAt)
        GROUP BY si.name
        ORDER BY totalPrice DESC
    """)
    fun findSummaryByItem(
        @Param("startAt") startAt: Instant?,
        @Param("endAt") endAt: Instant?,
        @Param("kioskId") kioskId: Long
    ): List<SaleByItemModel>
}

/*
SELECT NEW com.liveforpresent.cookiosk.api.sale.query.domain.SaleByItemModel(
si.name,
SUM(si.price * si.quantity),
SUM(si.quantity)
)
*/

// SELECT si.name name, SUM(si.price * si.quantity) totalPrice, SUM(si.quantity) totalQuantity
