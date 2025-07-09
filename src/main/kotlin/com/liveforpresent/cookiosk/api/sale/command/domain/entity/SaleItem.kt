package com.liveforpresent.cookiosk.api.sale.command.domain.entity

import com.liveforpresent.cookiosk.api.sale.command.domain.vo.SaleItemId
import com.liveforpresent.cookiosk.shared.core.domain.BaseEntity

class SaleItem(
    id: SaleItemId,
    private val props: SaleItemProps
): BaseEntity<SaleItemId>(id) {
    companion object {
        fun create(id: SaleItemId, props: SaleItemProps): SaleItem {
            val saleItem = SaleItem(id, props)
            saleItem.validate()

            return saleItem
        }
    }

    fun validate() {
        require(props.name.isNotBlank()) { "[SaleItem] 상품명은 필수입니다." }
        require(props.name.length < 32) { "[SaleItem] 상품명은 최대 31자 입니다."}

        require(props.price.value >= 0) { "[SaleItem] 상품 가격은 음수일 수 없습니다." }

        require(props.quantity > 0) { "[SaleItem] 상품 개수는 0보다 커야 합니다." }
    }
}