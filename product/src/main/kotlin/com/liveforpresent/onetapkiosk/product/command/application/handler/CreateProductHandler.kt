package com.liveforpresent.onetapkiosk.product.command.application.handler

import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Barcode
import com.liveforpresent.onetapkiosk.common.core.domain.vo.ImageUrl
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.ProductId
import com.liveforpresent.onetapkiosk.common.core.infrastructure.util.SnowflakeIdUtil
import com.liveforpresent.onetapkiosk.product.command.application.command.CreateProductCommand
import com.liveforpresent.onetapkiosk.product.command.domain.Product
import com.liveforpresent.onetapkiosk.product.command.domain.ProductCommandRepository
import com.liveforpresent.onetapkiosk.product.command.domain.ProductProps
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateProductHandler(
    private val productCommandRepository: ProductCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: CreateProductCommand) {
        val productId = ProductId(SnowflakeIdUtil.generateId())

        val productProps = ProductProps(
            name = command.name,
            price = Money.create(command.price),
            imageUrl = ImageUrl.create(command.imageUrl),
            displayOrder = command.displayOrder,
            barcode = Barcode.create(command.barcode),
            description = command.description,
            categoryId = command.categoryId,
            kioskId = KioskId(command.kioskId),
        )

        val product = Product.create(productId, productProps)

        productCommandRepository.save(product)

        eventPublisher.publish(product)
    }
}
