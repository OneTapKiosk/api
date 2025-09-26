package com.liveforpresent.onetapkiosk.product.command.application.handler

import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.product.command.application.command.DeleteProductCommand
import com.liveforpresent.onetapkiosk.product.command.domain.ProductCommandRepository
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
