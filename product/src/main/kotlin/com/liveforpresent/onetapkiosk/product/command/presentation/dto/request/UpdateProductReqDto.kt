package com.liveforpresent.onetapkiosk.product.command.presentation.dto.request

data class UpdateProductReqDto (
    val name: String?,
    val price: Int?,
    val imageUrl: String?,
    val isAvailable: Boolean?,
    val quantity: Int?,
    val displayOrder: Int?,
    val barcode: String?,
    val description: String?,
    val categoryId: Long?,
)
