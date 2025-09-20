package com.liveforpresent.onetapkiosk.product.command.presentation

import com.liveforpresent.onetapkiosk.product.command.application.command.CreateProductCommand
import com.liveforpresent.onetapkiosk.product.command.application.command.DeleteProductCommand
import com.liveforpresent.onetapkiosk.product.command.application.command.UpdateProductCommand
import com.liveforpresent.onetapkiosk.product.command.application.handler.CreateProductHandler
import com.liveforpresent.onetapkiosk.product.command.application.handler.DeleteProductHandler
import com.liveforpresent.onetapkiosk.product.command.application.handler.UpdateProductHandler
import com.liveforpresent.onetapkiosk.product.command.presentation.dto.request.CreateProductReqDto
import com.liveforpresent.onetapkiosk.product.command.presentation.dto.request.UpdateProductReqDto
import com.liveforpresent.onetapkiosk.common.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductCommandController(
    private val createProductHandler: CreateProductHandler,
    private val updateProductHandler: UpdateProductHandler,
    private val deleteProductHandler: DeleteProductHandler,
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
            categoryId = createProductReqDto.categoryId,
            kioskId = createProductReqDto.kioskId
        )

        createProductHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "상품 생성 성공"
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PatchMapping("/{productId}")
    fun updateProduct(@PathVariable productId: Long, @RequestBody updateProductReqDto: UpdateProductReqDto): ResponseEntity<BaseApiResponse<Unit>> {
        val command = UpdateProductCommand(
            productId = productId,
            name = updateProductReqDto.name,
            price = updateProductReqDto.price,
            imageUrl = updateProductReqDto.imageUrl,
            displayOrder = updateProductReqDto.displayOrder,
            barcode = updateProductReqDto.barcode,
            description = updateProductReqDto.description,
            categoryId = updateProductReqDto.categoryId,
        )

        updateProductHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "상품 수정 성공"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @DeleteMapping("/{productId}")
    fun deleteProduct(@PathVariable productId: Long): ResponseEntity<BaseApiResponse<Unit>> {
        val command = DeleteProductCommand(productId)

        deleteProductHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "상품 삭제 성공"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
