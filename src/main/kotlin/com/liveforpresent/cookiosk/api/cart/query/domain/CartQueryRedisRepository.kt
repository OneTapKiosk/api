package com.liveforpresent.cookiosk.api.cart.query.domain

interface CartQueryRedisRepository {
    fun findById(id: Long): CartModel
}
