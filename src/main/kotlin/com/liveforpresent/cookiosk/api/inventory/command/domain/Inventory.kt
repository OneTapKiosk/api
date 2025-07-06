package com.liveforpresent.cookiosk.api.inventory.command.domain

import com.liveforpresent.cookiosk.api.inventory.command.domain.vo.InventoryId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.AggregateRoot
import java.time.Instant

class Inventory private constructor(
    id: InventoryId,
    private val props: InventoryProps
): AggregateRoot<InventoryId>(id) {
    companion object {
        fun create(id: InventoryId, props: InventoryProps): Inventory {
            val inventory = Inventory(id, props)
            inventory.validate()

            return inventory
        }
    }

    fun validate() {
        require(props.quantity >= 0) { "[Inventory] 수량은 음수일 수 없습니다." }
    }

    fun increaseQuantity(amount: Int): Inventory {
        return Inventory(id, props.copy(
            quantity = props.quantity + amount,
            updatedAt = Instant.now()
        ))
    }

    fun decreaseQuantity(amount: Int): Inventory {
        require(amount > 0) { "[Inventory] 감소 수량은 0보다 커야 합니다." }
        require(props.quantity >= amount) { "[Inventory] 재고가 부족 합니다." }

        val newQuantity = props.quantity - amount
        val newIsAvailable = !(newQuantity == 0 && props.isAvailable)

        return Inventory(id, props.copy(
            isAvailable = newIsAvailable,
            quantity = newQuantity,
            updatedAt = Instant.now()
        ))
    }

    fun updateInventory(
        newIsAvailable: Boolean,
        newQuantity: Int,
        newProductId: ProductId
    ): Inventory {
        val updatedInventory = Inventory(id, props.copy(
            isAvailable = newIsAvailable,
            quantity = newQuantity,
            updatedAt = Instant.now()
        ))
        updatedInventory.validate()

        return updatedInventory
    }

    val isAvailable: Boolean get() = props.isAvailable
    val quantity: Int get() = props.quantity
    val productId: ProductId get() = props.productId
    val createdAt: Instant get() = props.createdAt
    val updatedAt: Instant get() = props.updatedAt
}
