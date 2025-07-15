package com.liveforpresent.cookiosk.api.product.command.infrastructure

import com.liveforpresent.cookiosk.api.product.command.domain.Product
import com.liveforpresent.cookiosk.api.product.command.domain.ProductCommandRepository
import jakarta.persistence.EntityNotFoundException
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

    override fun findOne(id: Long): Product {
        val productEntity = productCommandJpaRepository.findById(id).orElseThrow { EntityNotFoundException("[ProductCommandRepository] ${id}에 해당하는 상품을 찾을 수 없습니다") }

        return ProductEntity.toDomain(productEntity)
    }
}
