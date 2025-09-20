package com.liveforpresent.onetapkiosk.ordering.cart.command.domain

interface CartCommandRedisRepository {
    fun save(cart: Cart)
    fun deleteById(cartId: Long)
    fun findById(cartId: Long): Cart
}
