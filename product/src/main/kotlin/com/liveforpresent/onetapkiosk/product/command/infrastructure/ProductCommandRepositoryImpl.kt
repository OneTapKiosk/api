package com.liveforpresent.onetapkiosk.product.command.infrastructure

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import com.liveforpresent.onetapkiosk.product.command.domain.Product
import com.liveforpresent.onetapkiosk.product.command.domain.ProductCommandRepository
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
        val productEntity = productCommandJpaRepository.findById(id).orElseThrow {
            CustomException(
                CustomExceptionCode.PRODUCT_NOT_FOUND,
                "[ProductCommandRepository] ${id}에 해당하는 상품을 찾을 수 없습니다"
            )
        }

        return ProductEntity.toDomain(productEntity)
    }
}
