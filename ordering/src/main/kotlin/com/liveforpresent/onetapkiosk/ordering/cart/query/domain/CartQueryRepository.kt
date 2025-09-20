package com.liveforpresent.onetapkiosk.ordering.cart.query.domain

interface CartQueryRepository {
    fun findById(id: Long): CartModel
}
