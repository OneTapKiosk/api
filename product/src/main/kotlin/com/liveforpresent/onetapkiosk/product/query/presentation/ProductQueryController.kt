package com.liveforpresent.onetapkiosk.product.query.presentation

import com.liveforpresent.onetapkiosk.common.core.presentation.BaseApiResponse
import com.liveforpresent.onetapkiosk.product.query.application.handler.GetProductByBarcodeHandler
import com.liveforpresent.onetapkiosk.product.query.application.handler.GetProductByIdHandler
import com.liveforpresent.onetapkiosk.product.query.application.handler.GetProductByNameHandler
import com.liveforpresent.onetapkiosk.product.query.application.handler.GetProductListByCriteriaHandler
import com.liveforpresent.onetapkiosk.product.query.application.query.GetProductByBarcodeQuery
import com.liveforpresent.onetapkiosk.product.query.application.query.GetProductByIdQuery
import com.liveforpresent.onetapkiosk.product.query.application.query.GetProductByNameQuery
import com.liveforpresent.onetapkiosk.product.query.application.query.GetProductListByCriteriaQuery
import com.liveforpresent.onetapkiosk.product.query.domain.ProductModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductQueryController(
    private val getProductListByCriteriaHandler: GetProductListByCriteriaHandler,
    private val getProductByIdHandler: GetProductByIdHandler,
    private val getProductByNameHandler: GetProductByNameHandler,
    private val getProductByBarcodeHandler: GetProductByBarcodeHandler
) {
    @GetMapping
    fun getProductListByCriteria(
        @RequestParam(required = false) categoryId: Long?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) minPrice: Int?,
        @RequestParam(required = false) maxPrice: Int?,
        @RequestParam(required = false) sortBy: String?,
        @RequestParam(required = false) inStock: Boolean?
    ): ResponseEntity<BaseApiResponse<List<ProductModel>>> {
        val query = GetProductListByCriteriaQuery(name, minPrice, maxPrice, categoryId, sortBy)
        val result = getProductListByCriteriaHandler.execute(query)

        val response = BaseApiResponse<List<ProductModel>>(
            success = true,
            message = "상품 목록 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/{productId}")
    fun getProductById(@PathVariable productId: Long): ResponseEntity<BaseApiResponse<ProductModel>> {
        val query = GetProductByIdQuery(productId)
        val result = getProductByIdHandler.execute(query)

        val response = BaseApiResponse<ProductModel>(
            success = true,
            message = "상품 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/by-name/{productName}")
    fun getProductByName(@PathVariable productName: String): ResponseEntity<BaseApiResponse<ProductModel>> {
        val query = GetProductByNameQuery(productName)
        val result = getProductByNameHandler.execute(query)

        val response = BaseApiResponse<ProductModel>(
            success = true,
            message = "상품 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/by-barcode/{productBarcode}")
    fun getProductByBarcode(@PathVariable productBarcode: String): ResponseEntity<BaseApiResponse<ProductModel>> {
        val query = GetProductByBarcodeQuery(productBarcode)
        val result = getProductByBarcodeHandler.execute(query)

        val response = BaseApiResponse<ProductModel>(
            success = true,
            message = "상품 조회 성공",
            data = result
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
