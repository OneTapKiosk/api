package com.liveforpresent.cookiosk.api.product.command.domain

import com.liveforpresent.cookiosk.api.product.command.domain.vo.Barcode
import com.liveforpresent.cookiosk.shared.core.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.AggregateRoot

class Product private constructor(
    id: ProductId,
    private val props: ProductProps,
) : AggregateRoot<ProductId>(id) {
    companion object {
        fun create(id: ProductId, props: ProductProps): Product {
            val product = Product(id, props)
            product.validate()

            return product
        }
    }

    fun validate() {
        require(props.name.isNotBlank()) { "[Product] 상품명은 필수입니다." }
        require(props.name.length < 32) { "[Product] 상품명은 최대 31자 입니다." }

        require(props.price.value >= 0) { "[Product] 상품 가격은 음수일 수 없습니다." }

        require(props.displayOrder >= 0) { "[Product] 상품 정렬 순서는 음수일 수 없습니다." }

        require((props.description?.length ?: 0) < 128) { "[Product] 상품 설명은 최대 127자 입니다." }
    }

    fun updateProduct(
        newName: String = this.name,
        newPrice: Money = this.price,
        newImageUrl: ImageUrl = this.imageUrl,
        newDisplayOrder: Int = this.displayOrder,
        newBarcode: Barcode = this.barcode,
        newDescription: String? = this.description,
        newCategoryId: String? = this.categoryId,
    ): Product {
        val updatedProduct = Product(
            id, props.copy(
                name = newName,
                price = newPrice,
                imageUrl = newImageUrl,
                displayOrder = newDisplayOrder,
                barcode = newBarcode,
                description = newDescription,
                categoryId = newCategoryId,
            )
        )
        updatedProduct.validate()

        return updatedProduct
    }

    val productId: ProductId get() = id
    val name: String get() = props.name
    val price: Money get() = props.price
    val imageUrl: ImageUrl get() = props.imageUrl
    val displayOrder: Int get() = props.displayOrder
    val barcode: Barcode get() = props.barcode
    val description: String? get() = props.description
    val categoryId: String? get() = props.categoryId
}