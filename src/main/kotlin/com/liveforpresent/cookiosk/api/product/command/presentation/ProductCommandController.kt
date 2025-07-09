package com.liveforpresent.cookiosk.api.product.command.presentation

import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductCommandController {
    @PostMapping
    fun createProduct(@RequestBody createProductReqDto: Any): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.CREATED)
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
