package com.liveforpresent.onetapkiosk.product.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.AggregateRoot
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Barcode
import com.liveforpresent.onetapkiosk.common.core.domain.vo.ImageUrl
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import com.liveforpresent.onetapkiosk.product.command.domain.event.*
import java.time.Instant

class Product private constructor(
    id: ProductId,
    private val props: ProductProps,
) : AggregateRoot<ProductId>(id) {
    companion object {
        fun create(id: ProductId, props: ProductProps): Product {
            val product = Product(id, props)
            product.validate()

            product.addDomainEvent(
                ProductCreatedEvent(
                    productId = product.productId.value,
                    quantity = 0,
                    kioskId = product.kioskId.value
                )
            )

            return product
        }
    }

    fun validate() {
        require(props.name.isNotBlank()) { throw CustomException(
            CustomExceptionCode.PRODUCT_NAME_EMPTY,
            "[Product] 상품명은 필수입니다."
        ) }
        require(props.name.length < 32) { throw CustomException(
            CustomExceptionCode.PRODUCT_NAME_LENGTH_EXCEEDED,
            "[Product] 상품명은 최대 31자 입니다."
        ) }

        require(props.price.value >= 0) { throw CustomException(
            CustomExceptionCode.PRODUCT_PRICE_NEGATIVE,
            "[Product] 상품 가격은 음수일 수 없습니다."
        ) }

        require(props.quantity >= 0) { throw CustomException(
            CustomExceptionCode.PRODUCT_QUANTITY_NEGATIVE,
            "[Product] 수량은 음수일 수 없습니다."
        ) }

        require(props.displayOrder >= 0) { throw CustomException(
            CustomExceptionCode.PRODUCT_DISPLAY_ORDER_NEGATIVE,
            "[Product] 상품 정렬 순서는 음수일 수 없습니다."
        ) }

        require((props.description?.length ?: 0) < 128) { throw CustomException(
            CustomExceptionCode.PRODUCT_DESCRIPTION_LENGTH_EXCEEDED,
            "[Product] 상품 설명은 최대 127자 입니다."
        ) }
    }

    fun update(
        newName: String = this.name,
        newPrice: Money = this.price,
        newImageUrl: ImageUrl = this.imageUrl,
        newIsAvailable: Boolean = this.isAvailable,
        newQuantity: Int = this.quantity,
        newDisplayOrder: Int = this.displayOrder,
        newBarcode: Barcode = this.barcode,
        newDescription: String? = this.description,
        newCategoryId: Long? = this.categoryId,
        newKioskId: KioskId = this.kioskId,
    ): Product {
        val updatedProduct = Product(
            id, props.copy(
                name = newName,
                price = newPrice,
                imageUrl = newImageUrl,
                isAvailable = newIsAvailable,
                quantity = newQuantity,
                displayOrder = newDisplayOrder,
                barcode = newBarcode,
                description = newDescription,
                categoryId = newCategoryId,
                kioskId = newKioskId,
                updatedAt = Instant.now()
            )
        )
        updatedProduct.validate()

        updatedProduct.addDomainEvent(ProductUpdatedEvent())

        return updatedProduct
    }

    fun delete(id: ProductId): Product {
        val updatedProduct = Product(
            id, props.copy(
                isDeleted = true,
                deletedAt = Instant.now(),
            )
        )
        updatedProduct.addDomainEvent(ProductDeletedEvent(updatedProduct.productId.value))

        return updatedProduct
    }

    val productId: ProductId get() = id
    val name: String get() = props.name
    val price: Money get() = props.price
    val imageUrl: ImageUrl get() = props.imageUrl
    val isAvailable: Boolean get() = props.isAvailable
    val quantity: Int get() = props.quantity
    val displayOrder: Int get() = props.displayOrder
    val barcode: Barcode get() = props.barcode
    val description: String? get() = props.description
    val categoryId: Long? get() = props.categoryId
    val kioskId: KioskId get() = props.kioskId
    val createdAt: Instant get() = props.createdAt
    val updatedAt: Instant get() = props.updatedAt
    val isDeleted: Boolean get() = props.isDeleted
    val deletedAt: Instant? get() = props.deletedAt
}