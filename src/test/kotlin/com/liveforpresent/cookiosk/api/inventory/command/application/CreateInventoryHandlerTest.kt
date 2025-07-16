package com.liveforpresent.cookiosk.api.inventory.command.application

import com.liveforpresent.cookiosk.api.inventory.command.application.command.CreateInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.application.handler.CreateInventoryHandler
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryCommandRepository
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import java.time.Instant

class CreateInventoryHandlerTest : BehaviorSpec({

    val inventoryCommandRepository = mockk<InventoryCommandRepository>(relaxed = true) // Mock Repository
    val createInventoryHandler = CreateInventoryHandler(inventoryCommandRepository)

    beforeSpec {
        mockkObject(SnowflakeIdUtil)
    }

    afterSpec {
        unmockkObject(SnowflakeIdUtil)
    }

    Given("재고 생성 커맨드가 주어졌을 때") {
        val productId = 1L
        val kioskId = 1L
        val quantity = 10
        val isAvailable = true
        val now = Instant.now()
        val generatedInventoryId = 1L

        val command = CreateInventoryCommand(
            isAvailable = isAvailable,
            quantity = quantity,
            productId = productId,
            kioskId = kioskId,
            createdAt = now,
            updatedAt = now
        )

        every { SnowflakeIdUtil.generateId() } returns generatedInventoryId

        When("핸들러의 execute 메서드를 실행하면") {
            createInventoryHandler.execute(command)

            Then("InventoryCommandRepository의 save 메서드가 호출되어야 한다") {
                verify(exactly = 1) {
                    inventoryCommandRepository.save(
                        withArg { inventory ->
                            inventory.id.value shouldBe generatedInventoryId
                            inventory.isAvailable shouldBe isAvailable
                            inventory.quantity shouldBe quantity
                            inventory.productId.value shouldBe productId
                            inventory.kioskId.value shouldBe kioskId
                            inventory.createdAt shouldBe now
                            inventory.updatedAt shouldBe now
                        }
                    )
                }
            }
        }
    }
})
