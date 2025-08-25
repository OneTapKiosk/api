package com.liveforpresent.cookiosk.api.inventory.command.infrastructure

import com.liveforpresent.cookiosk.api.inventory.command.domain.Inventory
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryProps
import com.liveforpresent.cookiosk.api.inventory.command.domain.vo.InventoryId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.Instant

class InventoryCommandRepositoryImplTest: BehaviorSpec({
    val inventoryCommandJpaRepository = mockk<InventoryCommandJpaRepository>()
    val inventoryCommandRepositoryImpl = InventoryCommandRepositoryImpl(inventoryCommandJpaRepository)

    given("a inventory") {
        val inventoryId = InventoryId(1L)

        val inventoryProps = InventoryProps(
            isAvailable = true,
            quantity = 1,
            productId = ProductId(1L),
            kioskId = KioskId(1L),
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

        val inventory = Inventory.create(inventoryId, inventoryProps)

        val entityReturnedFromJpa = InventoryEntity(
            id = inventory.id.value,
            isAvailable = inventory.isAvailable,
            quantity = inventory.quantity,
            productId = inventory.productId.value,
            kioskId =  inventory.kioskId.value,
            createdAt = inventory.createdAt,
            updatedAt = inventory.updatedAt,
            isDeleted = false,
            deletedAt = null
        )

        val domainReturnedFromJpa = InventoryEntity.toDomain(entityReturnedFromJpa)

        every { inventoryCommandJpaRepository.save(any<InventoryEntity>()) } returns entityReturnedFromJpa

        `when`("DB에 inventory를 저장한다") {
            val result = inventoryCommandRepositoryImpl.save(inventory)

            then("inventory 정보를 return한다.") {
                result.id shouldBe domainReturnedFromJpa.id
                result.isAvailable shouldBe domainReturnedFromJpa.isAvailable
                result.quantity shouldBe domainReturnedFromJpa.quantity
                result.productId shouldBe domainReturnedFromJpa.productId
                result.kioskId shouldBe domainReturnedFromJpa.kioskId
                result.createdAt shouldBe domainReturnedFromJpa.createdAt
                result.updatedAt shouldBe domainReturnedFromJpa.updatedAt
            }
        }
    }
})
