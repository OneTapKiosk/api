package com.liveforpresent.cookiosk.api.product.query.domain

data class ProductModel(
    val id: String,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val displayOrder: Int,
    val barcode: String,
    val description: String?,
    val categoryId: String?
)
