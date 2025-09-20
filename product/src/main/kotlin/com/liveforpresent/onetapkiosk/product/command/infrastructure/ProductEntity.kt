package com.liveforpresent.onetapkiosk.product.command.infrastructure

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Barcode
import com.liveforpresent.onetapkiosk.common.core.domain.vo.ImageUrl
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.product.command.domain.Product
import com.liveforpresent.onetapkiosk.product.command.domain.ProductProps
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    @Column(nullable = false)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val imageUrl: String,

    @Column(nullable = false)
    val displayOrder: Int,

    @Column(nullable = false)
    val barcode: String,

    @Column(nullable = true)
    val description: String?,

    @Column(nullable = true)
    val categoryId: Long?,

    @Column(nullable = false)
    val kioskId: Long,

    @Column(nullable = false)
    val isDeleted: Boolean,

    @Column(nullable = true)
    val deletedAt: Instant?
) {
    companion object {
        fun toPersistence(product: Product): ProductEntity {
            return ProductEntity(
                id = product.id.value,
                name = product.name,
                price = product.price.value,
                imageUrl = product.imageUrl.value,
                displayOrder = product.displayOrder,
                barcode = product.barcode.value,
                description = product.description,
                categoryId = product.categoryId,
                kioskId = product.kioskId.value,
                isDeleted = product.isDeleted,
                deletedAt = product.deletedAt,
            )
        }

        fun toDomain(productEntity: ProductEntity): Product {
            val props = ProductProps(
                name = productEntity.name,
                price = Money.create(productEntity.price),
                imageUrl = ImageUrl.create(productEntity.imageUrl),
                displayOrder = productEntity.displayOrder,
                barcode = Barcode.create(productEntity.barcode),
                description = productEntity.description,
                categoryId = productEntity.categoryId,
                kioskId = KioskId(productEntity.kioskId),
                isDeleted = productEntity.isDeleted,
                deletedAt = productEntity.deletedAt,
            )

            return Product.create(ProductId(productEntity.id), props)
        }
    }
}
