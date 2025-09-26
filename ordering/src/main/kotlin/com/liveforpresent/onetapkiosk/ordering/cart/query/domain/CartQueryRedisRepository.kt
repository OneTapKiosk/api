package com.liveforpresent.onetapkiosk.ordering.cart.query.domain

interface CartQueryRedisRepository {
    fun findById(id: Long): CartModel
}
