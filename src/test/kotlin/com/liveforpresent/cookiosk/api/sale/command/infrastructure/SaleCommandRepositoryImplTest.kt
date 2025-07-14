package com.liveforpresent.cookiosk.api.sale.command.infrastructure

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.sale.command.domain.Sale
import com.liveforpresent.cookiosk.api.sale.command.domain.SaleProps
import com.liveforpresent.cookiosk.api.sale.command.domain.entity.SaleItem
import com.liveforpresent.cookiosk.api.sale.command.domain.entity.SaleItemProps
import com.liveforpresent.cookiosk.api.sale.command.domain.vo.SaleId
import com.liveforpresent.cookiosk.api.sale.command.domain.vo.SaleItemId
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.Instant

class SaleCommandRepositoryImplTest: BehaviorSpec({
    val saleCommandJpaRepository = mockk<SaleCommandJpaRepository>()
    val saleCommandRepositoryImpl = SaleCommandRepositoryImpl(saleCommandJpaRepository)

    given("a sale") {
        val saleId = SaleId(1L)
        val saleItemId = SaleItemId(1L)

        val saleItemProps = SaleItemProps(
            name = "name",
            quantity = 1,
            price = Money.create(1)
        )

        val saleItem = SaleItem(saleItemId, saleItemProps)

        val saleProps = SaleProps(
            totalPrice = Money.create(100),
            saleItems = mutableListOf(saleItem),
            kioskId = KioskId(1L),
            createdAt = Instant.now(),
        )

        val sale = Sale.create(saleId, saleProps)

        val entityReturnedFromJpa = SaleEntity(
            id = sale.id.value,
            totalPrice = sale.totalPrice.value,
            saleItems = mutableListOf(SaleItemEntity.toPersistence(saleItem)),
            kioskId = sale.kioskId.value,
            createdAt = sale.createdAt
        )

        val domainReturnedFromJpa = SaleEntity.toDomain(entityReturnedFromJpa)

        every { saleCommandJpaRepository.save(any<SaleEntity>()) } returns entityReturnedFromJpa

        `when`("sale을 DB에 저장한다.") {
            val result = saleCommandRepositoryImpl.save(sale)

            result.id shouldBe sale.id
            result.totalPrice shouldBe sale.totalPrice
            result.kioskId shouldBe sale.kioskId
            result.createdAt shouldBe sale.createdAt
            result.saleItems shouldBe sale.saleItems
        }
    }
})
