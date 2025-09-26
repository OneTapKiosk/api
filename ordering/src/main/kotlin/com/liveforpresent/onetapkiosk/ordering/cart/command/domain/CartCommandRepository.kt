package com.liveforpresent.onetapkiosk.ordering.cart.command.domain

interface CartCommandRepository {
    fun save(cart: Cart): Cart
    fun findById(id: Long): Cart
}
