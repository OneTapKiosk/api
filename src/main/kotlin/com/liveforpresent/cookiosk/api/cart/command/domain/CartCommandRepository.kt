package com.liveforpresent.cookiosk.api.cart.command.domain

interface CartCommandRepository {
    fun save(cart: Cart): Cart
}
