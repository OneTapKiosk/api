package com.liveforpresent.cookiosk.api.product.command.infrastructure

import com.liveforpresent.cookiosk.api.product.command.domain.Product
import com.liveforpresent.cookiosk.api.product.command.domain.ProductCommandRepository
import org.springframework.stereotype.Repository

@Repository
class ProductCommandRepositoryImpl(
    private val productCommandJpaRepository: ProductCommandJpaRepository
): ProductCommandRepository {
    override fun save(product: Product): Product {
        val productEntity = ProductEntity.toPersistence(product)
        productCommandJpaRepository.save(productEntity)

        return product
    }
}
