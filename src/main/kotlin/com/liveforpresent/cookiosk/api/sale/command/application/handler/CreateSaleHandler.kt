package com.liveforpresent.cookiosk.api.sale.command.application.handler

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.sale.command.application.command.CreateSaleCommand
import com.liveforpresent.cookiosk.api.sale.command.domain.Sale
import com.liveforpresent.cookiosk.api.sale.command.domain.SaleCommandRepository
import com.liveforpresent.cookiosk.api.sale.command.domain.SaleProps
import com.liveforpresent.cookiosk.api.sale.command.domain.entity.SaleItem
import com.liveforpresent.cookiosk.api.sale.command.domain.entity.SaleItemProps
import com.liveforpresent.cookiosk.api.sale.command.domain.vo.SaleId
import com.liveforpresent.cookiosk.api.sale.command.domain.vo.SaleItemId
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class CreateSaleHandler(
    private val saleCommandRepository: SaleCommandRepository,
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute(command: CreateSaleCommand) {
        val saleItems = command.saleItems.map { SaleItem.create(
            SaleItemId(SnowflakeIdUtil.generateId()), SaleItemProps(
                name = it.name,
                price = Money.create(it.price),
                quantity = it.quantity
            )
        ) }.toMutableList()

        val saleProps = SaleProps(
            saleItems = saleItems,
            totalPrice = Money.create(command.totalPrice),
            kioskId = KioskId(command.kioskId),
            createdAt = Instant.now(),
        )

        val sale = Sale.create(SaleId(SnowflakeIdUtil.generateId()), saleProps)

        saleCommandRepository.save(sale)
    }
}
