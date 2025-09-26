package com.liveforpresent.onetapkiosk.product.query.application.handler

import com.liveforpresent.onetapkiosk.product.query.application.query.GetProductByIdQuery
import com.liveforpresent.onetapkiosk.product.query.domain.ProductModel
import com.liveforpresent.onetapkiosk.product.query.domain.ProductQueryRepository
import org.springframework.stereotype.Service

@Service
class GetProductByIdHandler(
    private val productQueryRepository: ProductQueryRepository
) {
    fun execute(query: GetProductByIdQuery): ProductModel {
        return productQueryRepository.findById(query.productId)
    }
}
