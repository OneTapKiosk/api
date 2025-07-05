package com.liveforpresent.cookiosk.api.cart.command.domain

import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartId
import com.liveforpresent.cookiosk.shared.core.domain.AggregateRoot

class Cart(
    id: CartId,
    private val props: CartProps
): AggregateRoot<CartId>(id) {
    companion object {
        fun create(id: CartId, props: CartProps): Cart {
            val cart = Cart(id, props)
            cart.validate()

            return cart
        }
    }

    fun validate () {}
}