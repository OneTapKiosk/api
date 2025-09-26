package com.liveforpresent.onetapkiosk.ordering.sale.query.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.Instant

interface SaleQueryJpaRepository: JpaRepository<SaleView, Long> {
    @Query("""
        SELECT s FROM SaleView s
        WHERE s.kioskId = :kioskId
        AND (s.createdAt >= COALESCE(:startAt, s.createdAt))
        AND (s.createdAt <= COALESCE(:endAt, s.createdAt))
        ORDER BY
            CASE WHEN :sortBy = 'CREATED_AT_ASC' THEN s.createdAt END ASC,
            CASE WHEN :sortBy = 'CREATED_AT_DESC' THEN s.createdAt END DESC,
            CASE WHEN :sortBy = 'TOTAL_PRICE_ASC' THEN s.totalPrice END ASC,
            CASE WHEN :sortBy = 'TOTAL_PRICE_DESC' THEN s.totalPrice END DESC,
            s.createdAt DESC
    """)
    fun findByCriteria(
        @Param("startAt") startAt: Instant?,
        @Param("endAt") endAt: Instant?,
        @Param("sortBy") sortBy: String?,
        @Param("kioskId") kioskId: Long
    ): List<SaleView>
}

/*
SELECT NEW com.liveforpresent.onetapkiosk.ordering.sale.query.domain.SaleByItemModel(
si.name,
SUM(si.price * si.quantity),
SUM(si.quantity)
)
*/

// SELECT si.name name, SUM(si.price * si.quantity) totalPrice, SUM(si.quantity) totalQuantity
