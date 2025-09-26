package com.liveforpresent.onetapkiosk.ordering.cart.query.application.handler

import com.liveforpresent.onetapkiosk.ordering.cart.query.application.query.GetCartByIdQuery
import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartModel
import com.liveforpresent.onetapkiosk.ordering.cart.query.domain.CartQueryRedisRepository
import org.springframework.stereotype.Service

@Service
class GetCartByIdHandler(
    private val cartQueryRedisRepository: CartQueryRedisRepository
) {
    fun execute(query: GetCartByIdQuery): CartModel {
        return cartQueryRedisRepository.findById(query.cartId)
    }
}
