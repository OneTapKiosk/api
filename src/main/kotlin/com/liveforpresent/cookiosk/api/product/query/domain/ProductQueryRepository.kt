package com.liveforpresent.cookiosk.api.product.query.domain

interface ProductQueryRepository {
    fun findByCriteria(
        name: String?,
        minPrice: Int?,
        maxPrice: Int?,
        categoryId: Long?,
        sortBy: String?,): List<ProductModel>
    fun findById(id: Long): ProductModel
    fun findByName(name: String): ProductModel
    fun findByBarcode(barcode: String): ProductModel
    fun refreshView()
}
