package com.liveforpresent.onetapkiosk.product.command.application.command

data class UpdateProductCommand (
    val productId: Long,
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
