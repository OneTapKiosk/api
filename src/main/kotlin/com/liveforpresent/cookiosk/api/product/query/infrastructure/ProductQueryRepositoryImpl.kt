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
        val productEntities = productQueryJpaRepository.findByCriteria(name, minPrice, maxPrice, categoryId, sortBy)

        return productEntities.map { ProductModel(
            id = it.id.toString(),
            name = it.name,
            price = it.price,
            imageUrl = it.imageUrl,
            displayOrder = it.displayOrder,
            barcode = it.barcode,
            description = it.description,
            categoryId = it.categoryId.toString()
        ) }
    }

    override fun findById(id: Long): ProductModel {
        val productEntity = productQueryJpaRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow { IllegalArgumentException("해당 상품이 존재 하지 않습니다.") }

        return ProductModel(
            id = productEntity.id.toString(),
            name = productEntity.name,
            price = productEntity.price,
            imageUrl = productEntity.imageUrl,
            displayOrder = productEntity.displayOrder,
            barcode = productEntity.barcode,
            description = productEntity.description,
            categoryId = productEntity.categoryId.toString()
        )
    }

    override fun findByName(name: String): ProductModel {
        val productEntity = productQueryJpaRepository.findByNameAndIsDeletedFalse(name)
            .orElseThrow { IllegalArgumentException("해당 상품이 존재 하지 않습니다.") }

        return ProductModel(
            id = productEntity.id.toString(),
            name = productEntity.name,
            price = productEntity.price,
            imageUrl = productEntity.imageUrl,
            displayOrder = productEntity.displayOrder,
            barcode = productEntity.barcode,
            description = productEntity.description,
            categoryId = productEntity.categoryId.toString()
        )
    }

    override fun findByBarcode(barcode: String): ProductModel {
        val productEntity = productQueryJpaRepository.findByBarcodeAndIsDeletedFalse(barcode)
            .orElseThrow { IllegalArgumentException("해당 상품이 존재 하지 않습니다.") }

        return ProductModel(
            id = productEntity.id.toString(),
            name = productEntity.name,
            price = productEntity.price,
            imageUrl = productEntity.imageUrl,
            displayOrder = productEntity.displayOrder,
            barcode = productEntity.barcode,
            description = productEntity.description,
            categoryId = productEntity.categoryId.toString()
        )
    }
}
