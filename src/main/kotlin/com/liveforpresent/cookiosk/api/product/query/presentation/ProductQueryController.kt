package com.liveforpresent.cookiosk.api.product.query.presentation

import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductQueryController {
    @GetMapping
    fun getProductList(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) sort: String?,
        @RequestParam(required = false) inStock: Boolean?
    ): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/{productId}")
    fun getProduct(@PathVariable productId: String): ResponseEntity<BaseApiResponse<Unit>> {
        return ResponseEntity(HttpStatus.OK)
    }
}
