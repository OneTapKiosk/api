package com.liveforpresent.cookiosk.api.cart.query.domain

interface CartQueryRepository {
    fun findById(id: Long): CartModel
}
