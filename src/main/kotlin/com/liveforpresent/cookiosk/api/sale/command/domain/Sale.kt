package com.liveforpresent.cookiosk.api.sale.command.domain

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.sale.command.domain.entity.SaleItem
import com.liveforpresent.cookiosk.api.sale.command.domain.vo.SaleId
import com.liveforpresent.cookiosk.shared.core.domain.AggregateRoot
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import java.time.Instant

class Sale private constructor (
    id: SaleId,
    private val props: SaleProps
): AggregateRoot<SaleId>(id) {
    companion object {
        fun create(id: SaleId, props: SaleProps): Sale {
            val sale = Sale(id, props)
            sale.validate()

            return sale
        }
    }

    fun validate() {
        require(totalPrice.value > 0) { "총 가격은 0보다 커야 합니다." }
    }

    val saleItems: MutableList<SaleItem> get() = props.saleItems
    val createdAt: Instant get() = props.createdAt
    val totalPrice: Money get() = props.totalPrice
    val kioskId: KioskId get() = props.kioskId
}