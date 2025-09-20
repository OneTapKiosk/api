package com.liveforpresent.onetapkiosk.product.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Barcode
import com.liveforpresent.onetapkiosk.common.core.domain.vo.ImageUrl
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
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
