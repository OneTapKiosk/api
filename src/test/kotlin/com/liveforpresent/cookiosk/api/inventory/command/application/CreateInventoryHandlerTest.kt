package com.liveforpresent.cookiosk.api.inventory.command.application

import com.liveforpresent.cookiosk.api.inventory.command.application.command.CreateInventoryCommand
import com.liveforpresent.cookiosk.api.inventory.command.application.handler.CreateInventoryHandler
import com.liveforpresent.cookiosk.api.inventory.command.domain.Inventory
import com.liveforpresent.cookiosk.api.inventory.command.domain.InventoryCommandRepository
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import java.time.Instant

class CreateInventoryHandlerTest : BehaviorSpec({
    val inventoryCommandRepository: InventoryCommandRepository = mockk<InventoryCommandRepository>()
    val eventPublisher = mockk<DomainEventPublisher>()
    val createInventoryHandler = CreateInventoryHandler(inventoryCommandRepository, eventPublisher)

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
        val generatedInventoryId = 1L

        val command = CreateInventoryCommand(
            isAvailable = isAvailable,
            quantity = quantity,
            productId = productId,
            kioskId = kioskId
        )

        every { SnowflakeIdUtil.generateId() } returns generatedInventoryId

        When("핸들러의 execute 메서드를 실행하면") {
            every { inventoryCommandRepository.save(any()) } returnsArgument 0
            every { eventPublisher.publish(any<Inventory>()) } just Runs
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
                        }
                    )
                }
                verify(exactly = 1) { eventPublisher.publish(any<Inventory>()) }
            }
        }
    }
})
