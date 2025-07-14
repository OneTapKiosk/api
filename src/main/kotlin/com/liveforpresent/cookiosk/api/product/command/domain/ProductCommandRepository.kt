package com.liveforpresent.cookiosk.api.product.command.domain

interface ProductCommandRepository {
    fun save(product: Product): Product
}
