package com.liveforpresent.cookiosk.api.product.query.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

@Entity
@Immutable
@Table(name = "vw_product")
class ProductView(
    @Id
    val id: Long,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val displayOrder: Int,
    val barcode: String,
    val description: String?,
    val categoryId: Long?
)
