package com.liveforpresent.onetapkiosk.product.command.domain

interface ProductCommandRepository {
    fun save(product: Product): Product
    fun findOne(id: Long): Product
}
