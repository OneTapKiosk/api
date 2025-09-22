package com.liveforpresent.onetapkiosk.product.command.application.handler

import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.product.command.application.command.DecreaseProductQuantityCommand
import com.liveforpresent.onetapkiosk.product.command.domain.ProductCommandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class DecreaseProductQuantityHandler(
    private val productCommandRepository: ProductCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute(command: DecreaseProductQuantityCommand) {
        val product = productCommandRepository.findOne(command.productId.toLong())
        val updatedProduct = product.decreaseQuantity(command.amount)

        productCommandRepository.save(updatedProduct)

        eventPublisher.publish(updatedProduct)
    }
}
