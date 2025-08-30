package com.liveforpresent.cookiosk.shared.exception

import org.springframework.http.HttpStatus

enum class CustomExceptionCode(
    val code: HttpStatus,
    val message: String
) {
    // Product
    PRODUCT_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[Product] 상품명은 필수입니다."),
    PRODUCT_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[Product] 상품명은 최대 31자 입니다."),
    PRODUCT_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[Product] 상품 가격은 음수일 수 없습니다."),
    PRODUCT_DISPLAY_ORDER_NEGATIVE(HttpStatus.BAD_REQUEST, "[Product] 상품 정렬 순서는 음수일 수 없습니다."),
    PRODUCT_DESCRIPTION_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[Product] 상품 설명은 최대 127자 입니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "[ProductCommandRepository] 해당 상품을 찾을 수 없습니다")
}
