package com.liveforpresent.onetapkiosk.ordering.order.command.domain.entity

import com.liveforpresent.onetapkiosk.common.core.domain.BaseEntity
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import com.liveforpresent.onetapkiosk.ordering.order.command.domain.vo.OrderItemId

class OrderItem private constructor(
    id: OrderItemId,
    private val props: OrderItemProps
): BaseEntity<OrderItemId>(id) {
    companion object {
        fun create(id: OrderItemId, props: OrderItemProps): OrderItem {
            val orderItem = OrderItem(id, props)
            orderItem.validate()

            return orderItem
        }
    }

    fun validate() {
        require(props.name.isNotBlank()) {
            throw CustomException(
                CustomExceptionCode.ORDER_ITEM_NAME_EMPTY,
                "[OrderItem] 상품명은 필수입니다."
            )
        }
        require(props.name.length < 32) {
            throw CustomException(
                CustomExceptionCode.ORDER_ITEM_NAME_LENGTH_EXCEEDED,
                "[OrderItem] 상품명은 최대 31자 입니다."
            )
        }

        require(props.price.value >= 0) {
            throw CustomException(
                CustomExceptionCode.ORDER_ITEM_PRICE_NEGATIVE,
                "[OrderItem] 상품 가격은 음수일 수 없습니다."
            )
        }
    }

    val name: String get() = props.name
    val price: Money get() = props.price
    val quantity: Int get() = props.quantity
    val productId: ProductId get() = props.productId
}
