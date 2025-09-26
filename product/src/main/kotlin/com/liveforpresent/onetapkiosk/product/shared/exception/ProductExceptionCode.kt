package com.liveforpresent.onetapkiosk.product.shared.exception

import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import org.springframework.http.HttpStatus

enum class ProductExceptionCode(
    override val code: HttpStatus,
    override val message: String,
): CustomExceptionCode {
    PRODUCT_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[Product] 상품명은 필수입니다."),
    PRODUCT_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[Product] 상품명은 최대 31자 입니다."),
    PRODUCT_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[Product] 상품 가격은 음수일 수 없습니다."),
    PRODUCT_DISPLAY_ORDER_NEGATIVE(HttpStatus.BAD_REQUEST, "[Product] 상품 정렬 순서는 음수일 수 없습니다."),
    PRODUCT_DESCRIPTION_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[Product] 상품 설명은 최대 127자 입니다."),
    PRODUCT_QUANTITY_NEGATIVE(HttpStatus.BAD_REQUEST, "[Product] 재고 수량은 음수일 수 없습니다."),
    PRODUCT_DECREASE_AMOUNT_NON_POSITIVE(HttpStatus.BAD_REQUEST, "[Product] 감소 수량은 0보다 커야 합니다."),
    PRODUCT_INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "[Product] 재고가 부족합니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "[ProductRepository] 해당 상품을 찾을 수 없습니다"),
}
