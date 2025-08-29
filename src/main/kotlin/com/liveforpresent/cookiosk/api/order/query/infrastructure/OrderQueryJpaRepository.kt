package com.liveforpresent.cookiosk.api.order.query.infrastructure

import com.liveforpresent.cookiosk.api.order.query.domain.OrderModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.Instant
import java.util.Optional

interface OrderQueryJpaRepository : JpaRepository<OrderView, Long> {
    @Query("""
        SELECT o FROM OrderView o
        WHERE o.id = :orderId
        """)
    fun findWithItemsById(@Param("orderId") orderId: Long): Optional<OrderView>

    @Query("""
        SELECT o FROM OrderView o
        WHERE (o.createdAt >= COALESCE(:startAt, o.createdAt))
        AND (o.createdAt <= COALESCE(:endAt, o.createdAt))
        AND (:#{#statuses == null || #statuses.isEmpty()} = TRUE OR o.status IN (:statuses))
        ORDER BY
            CASE WHEN :sortBy = 'TOTAL_PRICE_ASC' THEN o.totalPrice END ASC,
            CASE WHEN :sortBy = 'TOTAL_PRICE_DESC' THEN o.totalPrice END DESC,
            o.createdAt DESC
        """)
    fun findByCriteria(
        @Param("startAt") startAt: Instant?,
        @Param("endAt") endAt: Instant?,
        @Param("statuses") statuses: List<String>?,
        @Param("sortBy") sortBy: String?,
    ): List<OrderView>
}
