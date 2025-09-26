package com.liveforpresent.onetapkiosk.product.query.application.handler

import com.liveforpresent.onetapkiosk.product.query.application.query.GetProductByNameQuery
import com.liveforpresent.onetapkiosk.product.query.domain.ProductModel
import com.liveforpresent.onetapkiosk.product.query.domain.ProductQueryRepository
import org.springframework.stereotype.Service

@Service
class GetProductByNameHandler(
    private val productQueryRepository: ProductQueryRepository
) {
    fun execute(query: GetProductByNameQuery): ProductModel {
        return productQueryRepository.findByName(query.name)
    }
}
