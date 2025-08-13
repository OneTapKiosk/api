package com.liveforpresent.cookiosk.api.product.query.infrastructure

import com.liveforpresent.cookiosk.api.product.command.infrastructure.ProductEntity
import com.liveforpresent.cookiosk.api.product.query.domain.ProductModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface ProductQueryJpaRepository: JpaRepository<ProductEntity, Long> {
    fun findByIdAndIsDeletedFalse(id: Long): Optional<ProductEntity>
    fun findByNameAndIsDeletedFalse(name: String): Optional<ProductEntity>
    fun findByBarcodeAndIsDeletedFalse(barcode: String): Optional<ProductEntity>

    @Query("""
        SELECT p FROM ProductEntity p
        WHERE p.isDeleted = false
        AND (:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%')))
        AND (:minPrice IS NULL OR p.price >= :minPrice)
        AND (:maxPrice IS NULL OR p.price <= :maxPrice)
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
    ): List<ProductEntity>
}
