package com.liveforpresent.cookiosk.api.cart.query.application.handler

import com.liveforpresent.cookiosk.api.cart.query.application.query.GetCartByIdQuery
import com.liveforpresent.cookiosk.api.cart.query.domain.CartModel
import com.liveforpresent.cookiosk.api.cart.query.domain.CartQueryRedisRepository
import com.liveforpresent.cookiosk.api.cart.query.domain.CartQueryRepository
import org.springframework.stereotype.Service

@Service
class GetCartByIdHandler(
    private val cartQueryRedisRepository: CartQueryRedisRepository
) {
    fun execute(query: GetCartByIdQuery): CartModel {
        return cartQueryRedisRepository.findById(query.cartId)
    }
}
