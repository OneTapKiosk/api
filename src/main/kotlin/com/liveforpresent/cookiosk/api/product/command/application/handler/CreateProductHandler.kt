package com.liveforpresent.cookiosk.api.product.command.application.handler

import com.liveforpresent.cookiosk.api.product.command.application.command.CreateProductCommand
import com.liveforpresent.cookiosk.api.product.command.domain.Product
import com.liveforpresent.cookiosk.api.product.command.domain.ProductCommandRepository
import com.liveforpresent.cookiosk.api.product.command.domain.ProductProps
import com.liveforpresent.cookiosk.api.product.command.domain.vo.Barcode
import com.liveforpresent.cookiosk.api.product.command.domain.vo.ProductId
import com.liveforpresent.cookiosk.shared.core.domain.vo.ImageUrl
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateProductHandler(
    private val productCommandRepository: ProductCommandRepository
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
        )

        val product = Product.create(productId, productProps)

        productCommandRepository.save(product)
    }
}
