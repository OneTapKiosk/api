package com.liveforpresent.cookiosk.api.product.command.application.handler

import com.liveforpresent.cookiosk.api.product.command.application.command.UpdateProductCommand
import com.liveforpresent.cookiosk.api.product.command.domain.ProductCommandRepository
import com.liveforpresent.cookiosk.api.product.command.domain.vo.Barcode
import com.liveforpresent.cookiosk.shared.core.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import org.springframework.stereotype.Service

@Service
class UpdateProductHandler(
    private val productCommandRepository: ProductCommandRepository
) {
    fun execute(command: UpdateProductCommand) {
        val product = productCommandRepository.findOne(command.productId)

        val updatedProduct = product.updateProduct(
            newName = command.name ?: product.name,
            newPrice = command.price?.let { Money.create(it) } ?: product.price,
            newImageUrl = command.imageUrl?. let { ImageUrl.create(it) } ?: product.imageUrl,
            newDisplayOrder = command.displayOrder ?: product.displayOrder,
            newBarcode = command.barcode?.let { Barcode.create(it) } ?: product.barcode,
            newDescription = command.description ?: product.description,
            newCategoryId = command.categoryId ?: product.categoryId
        )

        productCommandRepository.save(updatedProduct)
    }
}
