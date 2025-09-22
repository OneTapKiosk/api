package com.liveforpresent.onetapkiosk.product.command.application.handler

import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Barcode
import com.liveforpresent.onetapkiosk.common.core.domain.vo.ImageUrl
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.product.command.application.command.UpdateProductCommand
import com.liveforpresent.onetapkiosk.product.command.domain.ProductCommandRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UpdateProductHandler(
    private val productCommandRepository: ProductCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: UpdateProductCommand) {
        val product = productCommandRepository.findOne(command.productId)

        val updatedProduct = product.update(
            newName = command.name ?: product.name,
            newPrice = command.price?.let { Money.create(it) } ?: product.price,
            newImageUrl = command.imageUrl?. let { ImageUrl.create(it) } ?: product.imageUrl,
            newIsAvailable = command.isAvailable ?: product.isAvailable,
            newQuantity = command.quantity ?: product.quantity,
            newDisplayOrder = command.displayOrder ?: product.displayOrder,
            newBarcode = command.barcode?.let { Barcode.create(it) } ?: product.barcode,
            newDescription = command.description ?: product.description,
            newCategoryId = command.categoryId ?: product.categoryId
        )

        productCommandRepository.save(updatedProduct)

        eventPublisher.publish(updatedProduct)
    }
}
