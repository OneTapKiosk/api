package com.liveforpresent.cookiosk.api.product.query.application.handler

import com.liveforpresent.cookiosk.api.product.query.application.query.GetProductByNameQuery
import com.liveforpresent.cookiosk.api.product.query.domain.ProductModel
import com.liveforpresent.cookiosk.api.product.query.domain.ProductQueryRepository
import org.springframework.stereotype.Service

@Service
class GetProductByNameHandler(
    private val productQueryRepository: ProductQueryRepository
) {
    fun execute(query: GetProductByNameQuery): ProductModel {
        return productQueryRepository.findByName(query.name)
    }
}
