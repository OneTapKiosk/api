package com.liveforpresent.cookiosk.api.cart.command.presentation

import com.liveforpresent.cookiosk.api.cart.command.application.command.CartItemCommand
import com.liveforpresent.cookiosk.api.cart.command.application.handler.AddItemHandler
import com.liveforpresent.cookiosk.api.cart.command.domain.entity.CartItem
import com.liveforpresent.cookiosk.api.cart.command.presentation.dto.request.AddCartItemReqDto
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cart")
class CartCommandController(
    private val addItemHandler: AddItemHandler
) {
    // Return type: cartId
    @PostMapping
    fun createCart(): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping("/{cartId}/items")
    fun addItem(@PathVariable cartId: Long, @RequestBody reqDto: AddCartItemReqDto): ResponseEntity<BaseApiResponse<Unit>> {
        val command = CartItemCommand(
            cartId = cartId,
            name = reqDto.name,
            price = reqDto.price,
            imageUrl = reqDto.imageUrl,
            quantity = reqDto.quantity,
            productId = reqDto.productId
        )

        addItemHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "장바구니 상품 추가 성공"
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PatchMapping("/{cartId}/items/{itemId}")
    fun updateItemQuantity(
        @PathVariable cartId: String,
        @PathVariable itemId: String,
        @RequestBody updateItemQuantityReqDto: Any
    ): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    fun deleteItem(@PathVariable cartId: String, @PathVariable itemId: String): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
