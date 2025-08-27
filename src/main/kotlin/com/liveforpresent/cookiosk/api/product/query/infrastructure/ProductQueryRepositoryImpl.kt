package com.liveforpresent.cookiosk.api.product.query.infrastructure

import com.liveforpresent.cookiosk.api.product.query.domain.ProductModel
import com.liveforpresent.cookiosk.api.product.query.domain.ProductQueryRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class ProductQueryRepositoryImpl(
    private val productQueryJpaRepository: ProductQueryJpaRepository,
    private val em: EntityManager
): ProductQueryRepository {
    override fun findByCriteria(
        name: String?,
        minPrice: Int?,
        maxPrice: Int?,
        categoryId: Long?,
        sortBy: String?,
    ): List<ProductModel> {
        val productViews = productQueryJpaRepository.findByCriteria(name, minPrice, maxPrice, categoryId, sortBy)

        return productViews.map { ProductModel(
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
        val productView = productQueryJpaRepository.findById(id)
            .orElseThrow { IllegalArgumentException("해당 상품이 존재 하지 않습니다.") }

        return ProductModel(
            id = productView.id.toString(),
            name = productView.name,
            price = productView.price,
            imageUrl = productView.imageUrl,
            displayOrder = productView.displayOrder,
            barcode = productView.barcode,
            description = productView.description,
            categoryId = productView.categoryId.toString()
        )
    }

    override fun findByName(name: String): ProductModel {
        val productView = productQueryJpaRepository.findByName(name)
            .orElseThrow { IllegalArgumentException("해당 상품이 존재 하지 않습니다.") }

        return ProductModel(
            id = productView.id.toString(),
            name = productView.name,
            price = productView.price,
            imageUrl = productView.imageUrl,
            displayOrder = productView.displayOrder,
            barcode = productView.barcode,
            description = productView.description,
            categoryId = productView.categoryId.toString()
        )
    }

    override fun findByBarcode(barcode: String): ProductModel {
        val productView = productQueryJpaRepository.findByBarcode(barcode)
            .orElseThrow { IllegalArgumentException("해당 상품이 존재 하지 않습니다.") }

        return ProductModel(
            id = productView.id.toString(),
            name = productView.name,
            price = productView.price,
            imageUrl = productView.imageUrl,
            displayOrder = productView.displayOrder,
            barcode = productView.barcode,
            description = productView.description,
            categoryId = productView.categoryId.toString()
        )
    }

    override fun refreshView() {
        em.createNativeQuery("REFRESH MATERIALIZED VIEW CONCURRENTLY vw_product")
            .executeUpdate()
    }
}
