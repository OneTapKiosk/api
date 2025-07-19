package com.liveforpresent.cookiosk.api.cart.query.presentation

import com.liveforpresent.cookiosk.api.cart.query.application.handler.GetCartByIdHandler
import com.liveforpresent.cookiosk.api.cart.query.application.query.GetCartByIdQuery
import com.liveforpresent.cookiosk.api.cart.query.domain.CartModel
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cart")
class CartQueryController(
    private val getCartByIdHandler: GetCartByIdHandler
) {
    @GetMapping("{cartId}")
    fun cartItems(@PathVariable cartId: Long): ResponseEntity<BaseApiResponse<CartModel>> {
        val query = GetCartByIdQuery(cartId)
        val result = getCartByIdHandler.execute(query)

        val response = BaseApiResponse<CartModel>(
            success = true,
            message = "Cart 정보 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
