package com.liveforpresent.cookiosk.api.cart.command.presentation

import com.liveforpresent.cookiosk.api.cart.command.application.command.AddCartItemCommand
import com.liveforpresent.cookiosk.api.cart.command.application.command.CreateCartCommand
import com.liveforpresent.cookiosk.api.cart.command.application.command.DeleteCartCommand
import com.liveforpresent.cookiosk.api.cart.command.application.command.RemoveCartItemCommand
import com.liveforpresent.cookiosk.api.cart.command.application.dto.AddItemResDto
import com.liveforpresent.cookiosk.api.cart.command.application.dto.CreateCartResDto
import com.liveforpresent.cookiosk.api.cart.command.application.handler.AddItemHandler
import com.liveforpresent.cookiosk.api.cart.command.application.handler.CreateCartHandler
import com.liveforpresent.cookiosk.api.cart.command.application.handler.DeleteCartHandler
import com.liveforpresent.cookiosk.api.cart.command.application.handler.RemoveItemHandler
import com.liveforpresent.cookiosk.api.cart.command.presentation.dto.request.AddCartItemReqDto
import com.liveforpresent.cookiosk.api.cart.command.presentation.dto.request.CreateCartReqDto
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
    private val createCartHandler: CreateCartHandler,
    private val deleteCartHandler: DeleteCartHandler,
    private val addItemHandler: AddItemHandler,
    private val removeItemHandler: RemoveItemHandler
) {
    @PostMapping
    fun createCart(@RequestBody reqDto: CreateCartReqDto): ResponseEntity<BaseApiResponse<CreateCartResDto>> {
        val command = CreateCartCommand(reqDto.kioskId)
        val data = createCartHandler.execute(command)

        val response = BaseApiResponse<CreateCartResDto>(
            success = true,
            message = "장바구니 생성 성공",
            data = data
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @DeleteMapping("/{cartId}")
    fun deleteCart(@PathVariable cartId: Long): ResponseEntity<BaseApiResponse<Unit>> {
        val command = DeleteCartCommand(cartId)

        deleteCartHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "장바구니 삭제 성공"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @PostMapping("/{cartId}/items")
    fun addItem(@PathVariable cartId: Long, @RequestBody reqDto: AddCartItemReqDto): ResponseEntity<BaseApiResponse<AddItemResDto>> {
        val command = AddCartItemCommand(
            cartId = cartId,
            name = reqDto.name,
            price = reqDto.price,
            imageUrl = reqDto.imageUrl,
            quantity = reqDto.quantity,
            productId = reqDto.productId.toLong()
        )

        val data = addItemHandler.execute(command)

        val response = BaseApiResponse<AddItemResDto>(
            success = true,
            message = "장바구니 상품 추가 성공",
            data = data
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

    @DeleteMapping("/{cartId}/items/{cartItemId}")
    fun deleteItem(@PathVariable cartId: Long, @PathVariable cartItemId: Long): ResponseEntity<BaseApiResponse<Unit>> {
        val command = RemoveCartItemCommand(
            cartId = cartId,
            cartItemId = cartItemId
        )

        removeItemHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "장바구니 상품 삭제 성공"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
