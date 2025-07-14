package com.liveforpresent.cookiosk.api.inventory.command.infrastructure

import com.liveforpresent.cookiosk.api.inventory.command.domain.Inventory
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryProps
import com.liveforpresent.cookiosk.api.inventory.command.domain.vo.InventoryId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "inventory")
class InventoryEntity(
    @Id
    @Column(nullable = false)
    val id: Long,

    @Column(nullable = false)
    val isAvailable: Boolean,

    @Column(nullable = false)
    val quantity: Int,

    @Column(nullable = false)
    val productId: Long,

    @Column(nullable = false)
    val kioskId: Long,

    @Column(nullable = false)
    val createdAt: Instant,

    @Column(nullable = false)
    val updatedAt: Instant
) {
    companion object {
        fun toPersistence(inventory: Inventory): InventoryEntity {
            return InventoryEntity(
                id = inventory.id.value,
                isAvailable = inventory.isAvailable,
                quantity = inventory.quantity,
                productId = inventory.productId.value,
                kioskId = inventory.kioskId.value,
                createdAt = inventory.createdAt,
                updatedAt = inventory.updatedAt,
            )
        }

        fun toDomain(inventoryEntity: InventoryEntity): Inventory {
            val props = InventoryProps(
                isAvailable = inventoryEntity.isAvailable,
                quantity = inventoryEntity.quantity,
                productId = ProductId(inventoryEntity.productId),
                kioskId = KioskId(inventoryEntity.kioskId),
                createdAt = inventoryEntity.createdAt,
                updatedAt = inventoryEntity.updatedAt
            )

            return Inventory.create(InventoryId(inventoryEntity.id), props)
        }
    }
}
