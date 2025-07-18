package com.liveforpresent.cookiosk.api.product.query.application.handler

import com.liveforpresent.cookiosk.api.product.query.application.query.GetProductByIdQuery
import com.liveforpresent.cookiosk.api.product.query.domain.ProductModel
import com.liveforpresent.cookiosk.api.product.query.domain.ProductQueryRepository
import org.springframework.stereotype.Service

@Service
class GetProductByIdHandler(
    private val productQueryRepository: ProductQueryRepository
) {
    fun execute(query: GetProductByIdQuery): ProductModel {
        return productQueryRepository.findById(query.productId)
    }
}
