package com.liveforpresent.cookiosk.api.product.command.application.command

data class CreateProductCommand (
    val name: String,
    val price: Int,
    val imageUrl: String,
    val displayOrder: Int,
    val barcode: String,
    val description: String?,
    val categoryId: Long?,
)
