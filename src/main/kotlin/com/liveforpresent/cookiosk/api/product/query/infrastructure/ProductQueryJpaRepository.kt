package com.liveforpresent.cookiosk.api.product.query.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface ProductQueryJpaRepository: JpaRepository<ProductView, Long> {
    fun findByName(name: String): Optional<ProductView>
    fun findByBarcode(barcode: String): Optional<ProductView>

    @Query("""
        SELECT p FROM ProductView p
        WHERE (:name IS NULL OR p.name ILIKE concat('%', cast(:name as string), '%'))
        AND (:maxPrice IS NULL OR p.price <= :maxPrice)
        AND (:minPrice IS NULL OR p.price >= :minPrice)
        AND (:categoryId IS NULL OR p.categoryId = :categoryId)
        ORDER BY
            CASE WHEN :sortBy = 'NAME_ASC' THEN p.name END ASC,
            CASE WHEN :sortBy = 'NAME_DESC' THEN p.name END DESC,
            CASE WHEN :sortBy = 'DISPLAY_ORDER_ASC' THEN p.displayOrder END ASC,
            CASE WHEN :sortBy = 'DISPLAY_ORDER_DESC' THEN p.displayOrder END DESC,
            CASE WHEN :sortBy = 'PRICE_ASC' THEN p.price END ASC,
            CASE WHEN :sortBy = 'PRICE_DESC' THEN p.price END DESC,
            p.displayOrder ASC
    """)
    fun findByCriteria(
        @Param("name") name: String?,
        @Param("minPrice") minPrice: Int?,
        @Param("maxPrice") maxPrice: Int?,
        @Param("categoryId") categoryId: Long?,
        @Param("sortBy") sortBy: String?
    ): List<ProductView>
}
