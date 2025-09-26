package com.liveforpresent.onetapkiosk.ordering.sale.command.domain

import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.entity.SaleItem
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.event.SaleCreatedEvent
import com.liveforpresent.onetapkiosk.common.core.domain.AggregateRoot
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.SaleId
import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.ordering.shared.exception.SaleExceptionCode
import java.time.Instant

class Sale private constructor (
    id: SaleId,
    private val props: SaleProps
): AggregateRoot<SaleId>(id) {
    companion object {
        fun create(id: SaleId, props: SaleProps): Sale {
            val sale = Sale(id, props)
            sale.validate()

            sale.addDomainEvent(SaleCreatedEvent())

            return sale
        }
    }

    fun validate() {
        require(totalPrice.value > 0) {
            throw CustomException(
                SaleExceptionCode.SALE_TOTAL_PRICE_NON_POSITIVE,
                "[Sale] 총 가격은 0보다 커야 합니다."
            )
        }
    }

    val saleItems: MutableList<SaleItem> get() = props.saleItems
    val createdAt: Instant get() = props.createdAt
    val totalPrice: Money get() = props.totalPrice
    val kioskId: KioskId get() = props.kioskId
}