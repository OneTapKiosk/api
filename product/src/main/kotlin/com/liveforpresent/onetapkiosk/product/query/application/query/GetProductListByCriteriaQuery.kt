package com.liveforpresent.onetapkiosk.product.query.application.query

data class GetProductListByCriteriaQuery(
    val name: String?,
    val minPrice: Int?,
    val maxPrice: Int?,
    val categoryId: Long?,
    val sortBy: String?
)
