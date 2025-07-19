package com.liveforpresent.cookiosk.api.inventory.query.infrastructure

import com.liveforpresent.cookiosk.api.inventory.command.infrastructure.InventoryEntity
import com.liveforpresent.cookiosk.api.inventory.query.domain.InventoryModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface InventoryQueryJpaRepository: JpaRepository<InventoryEntity, Long> {
    @Query("""
        SELECT i FROM InventoryEntity i
        WHERE (:isAvailable IS NULL OR i.isAvailable = :isAvailable)
        AND i.isDeleted = false
        ORDER BY
        CASE WHEN :sortBy = 'QUANTITY_ASC' THEN i.quantity END ASC,
        CASE WHEN :sortBy = 'QUANTITY_DESC' THEN i.quantity END DESC,
        CASE WHEN :sortBy = 'CREATED_AT_ASC' THEN i.createdAt END ASC,
        CASE WHEN :sortBy = 'CREATED_AT_DESC' THEN i.createdAt END DESC,
        CASE WHEN :sortBy = 'UPDATED_AT_ASC' THEN i.createdAt END ASC,
        CASE WHEN :sortBy = 'UPDATED_AT_DESC' THEN i.createdAt END DESC,
        i.createdAt DESC
    """)
    fun findByCriteria(
        @Param("isAvailable") isAvailable: Boolean?,
        @Param("sortBy") sortBy: String?
    ): List<InventoryModel>
}
