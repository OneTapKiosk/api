package com.liveforpresent.cookiosk.api.product.query.infrastructure

import com.liveforpresent.cookiosk.api.product.query.domain.ProductModel
import com.liveforpresent.cookiosk.api.product.query.domain.ProductQueryRepository
import org.springframework.stereotype.Repository

@Repository
class ProductQueryRepositoryImpl(
    private val productQueryJpaRepository: ProductQueryJpaRepository
): ProductQueryRepository {
    override fun findByCriteria(
        name: String?,
        minPrice: Int?,
        maxPrice: Int?,
        categoryId: Long?,
        sortBy: String?,
    ): List<ProductModel> {
        return productQueryJpaRepository.findByCriteria(name, minPrice, maxPrice, categoryId, sortBy)
    }

    override fun findById(id: Long): ProductModel {
        return productQueryJpaRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow { IllegalArgumentException("해당 상품이 존재 하지 않습니다.") }
    }

    override fun findByName(name: String): ProductModel {
        return productQueryJpaRepository.findByNameAndIsDeletedFalse(name)
            .orElseThrow { IllegalArgumentException("해당 상품이 존재 하지 않습니다.") }
    }

    override fun findByBarcode(barcode: String): ProductModel {
        return productQueryJpaRepository.findByBarcodeAndIsDeletedFalse(barcode)
            .orElseThrow { IllegalArgumentException("해당 상품이 존재 하지 않습니다.") }
    }
}