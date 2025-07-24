package com.liveforpresent.cookiosk.api.product.command.application.handler

import com.liveforpresent.cookiosk.api.product.command.application.command.DeleteProductCommand
import com.liveforpresent.cookiosk.api.product.command.domain.ProductCommandRepository
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class DeleteProductHandler(
    private val productCommandRepository: ProductCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: DeleteProductCommand) {
        val product = productCommandRepository.findOne(command.id)
        val deletedProduct = product.delete(product.id)

        productCommandRepository.save(deletedProduct)

        eventPublisher.publish(deletedProduct)
    }
}
