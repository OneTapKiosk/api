package com.liveforpresent.onetapkiosk.product.query.application.handler

import com.liveforpresent.onetapkiosk.product.query.application.query.GetProductByBarcodeQuery
import com.liveforpresent.onetapkiosk.product.query.domain.ProductModel
import com.liveforpresent.onetapkiosk.product.query.domain.ProductQueryRepository
import org.springframework.stereotype.Service

@Service
class GetProductByBarcodeHandler(
    private val productQueryRepository: ProductQueryRepository
) {
    fun execute(query: GetProductByBarcodeQuery): ProductModel {
        return productQueryRepository.findByBarcode(query.barcode)
    }
}
