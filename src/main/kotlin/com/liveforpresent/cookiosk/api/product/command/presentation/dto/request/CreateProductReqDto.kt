package com.liveforpresent.cookiosk.api.product.command.presentation.dto.request

data class CreateProductReqDto (
    val name: String,
    val price: Int,
    val imageUrl: String,
    val displayOrder: Int,
    val barcode: String,
    val description: String?,
    val categoryId: Long?,
    val kioskId: Long
)
