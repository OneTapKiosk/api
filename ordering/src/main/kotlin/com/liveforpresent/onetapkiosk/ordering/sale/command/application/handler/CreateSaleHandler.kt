package com.liveforpresent.onetapkiosk.ordering.sale.command.application.handler

import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.SaleId
import com.liveforpresent.onetapkiosk.common.core.infrastructure.util.SnowflakeIdUtil
import com.liveforpresent.onetapkiosk.ordering.sale.command.application.command.CreateSaleCommand
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.Sale
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.SaleCommandRepository
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.SaleProps
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.entity.SaleItem
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.entity.SaleItemProps
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.vo.SaleItemId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class CreateSaleHandler(
    private val saleCommandRepository: SaleCommandRepository,
    private val eventPublisher: DomainEventPublisher
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

        eventPublisher.publish(sale)
    }
}
