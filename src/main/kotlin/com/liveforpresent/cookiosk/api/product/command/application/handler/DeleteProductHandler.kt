package com.liveforpresent.cookiosk.api.product.command.application.handler

import com.liveforpresent.cookiosk.api.product.command.application.command.DeleteProductCommand
import com.liveforpresent.cookiosk.api.product.command.domain.ProductCommandRepository
import org.springframework.stereotype.Service

@Service
class DeleteProductHandler(
    private val productCommandRepository: ProductCommandRepository
) {
    fun execute(command: DeleteProductCommand) {
        val product = productCommandRepository.findOne(command.id)
        val deletedProduct = product.delete(product.id)

        productCommandRepository.save(deletedProduct)
    }
}
