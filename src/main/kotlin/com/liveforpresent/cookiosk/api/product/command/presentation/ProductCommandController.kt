package com.liveforpresent.cookiosk.api.product.command.presentation

import com.liveforpresent.cookiosk.api.product.command.application.command.CreateProductCommand
import com.liveforpresent.cookiosk.api.product.command.application.handler.CreateProductHandler
import com.liveforpresent.cookiosk.api.product.command.presentation.dto.request.CreateProductReqDto
import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductCommandController(
    private val createProductHandler: CreateProductHandler,
) {
    @PostMapping
    fun createProduct(@RequestBody createProductReqDto: CreateProductReqDto): ResponseEntity<BaseApiResponse<Unit>> {
        val command = CreateProductCommand(
            name = createProductReqDto.name,
            price = createProductReqDto.price,
            imageUrl = createProductReqDto.imageUrl,
            displayOrder = createProductReqDto.displayOrder,
            barcode = createProductReqDto.barcode,
            description = createProductReqDto.description,
            categoryId = createProductReqDto.categoryId
        )

        createProductHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "상품 생성 성공"
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PatchMapping("/{productId}")
    fun updateProduct(@PathVariable productId: String, @RequestBody updateProductReqDto: Any): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: String): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
