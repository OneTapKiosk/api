package com.liveforpresent.cookiosk.api.order.command.domain.entity

import com.liveforpresent.cookiosk.api.order.command.domain.vo.OrderItemId
import com.liveforpresent.cookiosk.shared.core.domain.BaseEntity
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money

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
        require(props.name.isNotBlank()) { "[OrderItem] 상품명은 필수입니다." }
        require(props.name.length < 32) { "[OrderItem] 상품명은 최대 31자 입니다." }

        require(props.price.value >= 0) { "[OrderItem] 상품 가격은 음수일 수 없습니다." }
    }

    val name: String get() = props.name
    val price: Money get() = props.price
    val quantity: Int get() = props.quantity
}
