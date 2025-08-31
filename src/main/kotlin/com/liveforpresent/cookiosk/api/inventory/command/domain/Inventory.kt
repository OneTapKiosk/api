package com.liveforpresent.cookiosk.api.inventory.command.domain

import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryCreatedEvent
import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryDeletedEvent
import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryQuantityIncreasedEvent
import com.liveforpresent.cookiosk.api.inventory.command.domain.event.InventoryUpdatedEvent
import com.liveforpresent.cookiosk.api.inventory.command.domain.vo.InventoryId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.AggregateRoot
import com.liveforpresent.cookiosk.shared.exception.CustomException
import com.liveforpresent.cookiosk.shared.exception.CustomExceptionCode
import java.time.Instant

class Inventory private constructor(
    id: InventoryId,
    private val props: InventoryProps
): AggregateRoot<InventoryId>(id) {
    companion object {
        fun create(id: InventoryId, props: InventoryProps): Inventory {
            val inventory = Inventory(id, props)
            inventory.validate()
            inventory.addDomainEvent(InventoryCreatedEvent())

            return inventory
        }
    }

    fun validate() {
        require(props.quantity >= 0) {
            throw CustomException(
                CustomExceptionCode.INVENTORY_QUANTITY_NEGATIVE,
                "[Inventory] 수량은 음수일 수 없습니다."
            )
        }
    }

    fun increaseQuantity(amount: Int): Inventory {
        val updatedInventory = Inventory(id, props.copy(
            quantity = props.quantity + amount,
            updatedAt = Instant.now()
        ))

        updatedInventory.validate()

        updatedInventory.addDomainEvent(InventoryQuantityIncreasedEvent())

        return updatedInventory
    }

    fun decreaseQuantity(amount: Int): Inventory {
        require(amount > 0) {
            throw CustomException(
                CustomExceptionCode.INVENTORY_DECREASE_AMOUNT_NON_POSITIVE,
                "[Inventory] 감소 수량은 0보다 커야 합니다."
            )
        }
        require(props.quantity >= amount) {
            throw CustomException(
                CustomExceptionCode.INVENTORY_INSUFFICIENT_STOCK,
                "[Inventory] 재고가 부족 합니다."
            )
        }

        val newQuantity = props.quantity - amount
        val newIsAvailable = !(newQuantity == 0 && props.isAvailable)

        val updatedInventory = Inventory(id, props.copy(
            isAvailable = newIsAvailable,
            quantity = newQuantity,
            updatedAt = Instant.now()
        ))

        updatedInventory.validate()

        return updatedInventory
    }

    fun updateInventory(
        newIsAvailable: Boolean,
        newQuantity: Int,
        newProductId: ProductId
    ): Inventory {
        val updatedInventory = Inventory(id, props.copy(
            isAvailable = newIsAvailable,
            quantity = newQuantity,
            productId = newProductId,
            updatedAt = Instant.now()
        ))
        updatedInventory.validate()

        updatedInventory.addDomainEvent(InventoryUpdatedEvent())

        return updatedInventory
    }

    fun delete(id: InventoryId): Inventory {
        val updatedInventory = Inventory(id, props.copy(
            isDeleted = true,
            deletedAt = Instant.now()
        ))

        updatedInventory.addDomainEvent(InventoryDeletedEvent())

        return updatedInventory
    }

    val isAvailable: Boolean get() = props.isAvailable
    val quantity: Int get() = props.quantity
    val productId: ProductId get() = props.productId
    val kioskId: KioskId get() = props.kioskId
    val isDeleted: Boolean get() = props.isDeleted
    val createdAt: Instant get() = props.createdAt
    val updatedAt: Instant get() = props.updatedAt
    val deletedAt: Instant? get() = props.deletedAt
}
