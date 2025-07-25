package com.liveforpresent.cookiosk.api.cart.command.domain

interface CartCommandRedisRepository {
    fun save(cart: Cart)
    fun deleteById(cartId: Long)
    fun findById(cartId: Long): Cart
}
