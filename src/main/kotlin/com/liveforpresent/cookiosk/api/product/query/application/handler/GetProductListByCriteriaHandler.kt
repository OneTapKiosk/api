package com.liveforpresent.cookiosk.api.product.query.application.handler

import com.liveforpresent.cookiosk.api.product.query.application.query.GetProductListByCriteriaQuery
import com.liveforpresent.cookiosk.api.product.query.domain.ProductModel
import com.liveforpresent.cookiosk.api.product.query.domain.ProductQueryRepository
import org.springframework.stereotype.Service

@Service
class GetProductListByCriteriaHandler(
    private val productQueryRepository: ProductQueryRepository
) {
    fun execute(query: GetProductListByCriteriaQuery): List<ProductModel> {
        return productQueryRepository.findByCriteria(
            query.name,
            query.minPrice,
            query.maxPrice,
            query.categoryId,
            query.sortBy
        )
    }
}
