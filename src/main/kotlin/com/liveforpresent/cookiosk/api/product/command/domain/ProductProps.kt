package com.liveforpresent.cookiosk.api.product.command.domain

import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.api.product.command.domain.vo.Barcode
import com.liveforpresent.cookiosk.shared.core.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import java.time.Instant

data class ProductProps (
    val name: String,
    val price: Money,
    val imageUrl: ImageUrl,
    val displayOrder: Int,
    val barcode: Barcode,
    val description: String?,
    val categoryId: Long?,
    val kioskId: KioskId,
    val isDeleted: Boolean = false,
    val deletedAt: Instant? = null
)
