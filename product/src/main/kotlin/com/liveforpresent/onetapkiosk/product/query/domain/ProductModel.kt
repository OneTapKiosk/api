package com.liveforpresent.onetapkiosk.product.query.domain

import java.time.Instant

data class ProductModel(
    val id: String,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val isAvailable: Boolean,
    val quantity: Int,
    val displayOrder: Int,
    val barcode: String,
    val description: String?,
    val categoryId: String?,
    val createdAt: Instant,
    val updatedAt: Instant
)
