package com.liveforpresent.cookiosk.api.product.command.domain

import com.liveforpresent.cookiosk.api.product.command.domain.vo.Barcode
import com.liveforpresent.cookiosk.shared.core.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money

data class ProductProps (
    val name: String,
    val price: Money,
    val imageUrl: ImageUrl,
    val displayOrder: Int,
    val barcode: Barcode,
    val description: String?,
    val categoryId: Long?,
)
