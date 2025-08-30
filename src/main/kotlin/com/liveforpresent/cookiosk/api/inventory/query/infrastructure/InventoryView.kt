package com.liveforpresent.cookiosk.api.inventory.query.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.time.Instant

@Entity
@Immutable
@Table(name = "vw_inventory")
class InventoryView(
    @Id
    val id: Long,
    val isAvailable: Boolean,
    val quantity: Int,
    val productId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
)
