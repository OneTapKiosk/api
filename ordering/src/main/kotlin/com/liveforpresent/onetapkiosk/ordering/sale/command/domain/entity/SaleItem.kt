package com.liveforpresent.onetapkiosk.ordering.sale.command.domain.entity

import com.liveforpresent.onetapkiosk.common.core.domain.BaseEntity
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.vo.SaleItemId
import com.liveforpresent.onetapkiosk.ordering.shared.exception.SaleExceptionCode

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
        require(props.name.isNotBlank()) {
            throw CustomException(
                SaleExceptionCode.SALE_ITEM_NAME_EMPTY,
                "[SaleItem] 상품명은 필수입니다."
            )
        }
        require(props.name.length < 32) {
            throw CustomException(
                SaleExceptionCode.SALE_ITEM_NAME_LENGTH_EXCEEDED,
                "[SaleItem] 상품명은 최대 31자 입니다."
            )
        }

        require(props.price.value >= 0) {
            throw CustomException(
                SaleExceptionCode.SALE_ITEM_PRICE_NEGATIVE,
                "[SaleItem] 상품 가격은 음수일 수 없습니다."
            )
        }

        require(props.quantity > 0) {
            throw CustomException(
                SaleExceptionCode.SALE_ITEM_QUANTITY_NON_POSITIVE,
                "[SaleItem] 상품 수량은 0보다 커야 합니다."
            )
        }
    }

    val name: String get() = props.name
    val price: Money get() = props.price
    val quantity: Int get() = props.quantity
}