package com.liveforpresent.cookiosk.api.product.command.infrastructure

import com.liveforpresent.cookiosk.api.product.command.domain.Product
import com.liveforpresent.cookiosk.api.product.command.domain.ProductProps
import com.liveforpresent.cookiosk.api.product.command.domain.vo.Barcode
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ProductCommandRepositoryImplTest: BehaviorSpec({
    val productCommandJpaRepository = mockk<ProductCommandJpaRepository>()
    val productCommandRepositoryImpl = ProductCommandRepositoryImpl(productCommandJpaRepository)

    given("a product") {
        val productId = ProductId(1L)

        val productProps = ProductProps(
            name = "a",
            price = Money.create(1),
            imageUrl = ImageUrl.create("http://someimage.jpg"),
            displayOrder = 1,
            barcode = Barcode.create("1111111111111"),
            description = "a description",
            categoryId = 1L,
        )

        val product = Product.create(productId, productProps)

        val entityReturnedFromJpa = ProductEntity(
            id = product.id.value,
            name = product.name,
            price = product.price.value,
            imageUrl = product.imageUrl.value,
            displayOrder = product.displayOrder,
            barcode = product.barcode.value,
            description = product.description,
            categoryId = product.categoryId,
            isDeleted = false,
            deletedAt = null
        )

        val domainReturnedFromJpa = ProductEntity.toDomain(entityReturnedFromJpa)

        every { productCommandJpaRepository.save(any<ProductEntity>()) } returns entityReturnedFromJpa

        `when`("product를 db에 저장한다.") {
            val result = productCommandRepositoryImpl.save(product)

            then("product 정보를 return 한다.") {
                result.id shouldBe domainReturnedFromJpa.id
                result.name shouldBe domainReturnedFromJpa.name
                result.price shouldBe domainReturnedFromJpa.price
                result.imageUrl shouldBe domainReturnedFromJpa.imageUrl
                result.displayOrder shouldBe domainReturnedFromJpa.displayOrder
                result.barcode shouldBe domainReturnedFromJpa.barcode
                result.description shouldBe domainReturnedFromJpa.description
                result.categoryId shouldBe domainReturnedFromJpa.categoryId
            }
        }
    }
})
