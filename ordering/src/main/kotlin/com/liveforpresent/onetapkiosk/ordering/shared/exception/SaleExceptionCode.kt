package com.liveforpresent.onetapkiosk.ordering.shared.exception

import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import org.springframework.http.HttpStatus

enum class SaleExceptionCode(
    override val code: HttpStatus,
    override val message: String
): CustomExceptionCode {
    SALE_TOTAL_PRICE_NON_POSITIVE(HttpStatus.BAD_REQUEST, "[Sale] 총 가격은 0보다 커야 합니다."),

    SALE_ITEM_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[SaleItem] 상품명은 필수입니다."),
    SALE_ITEM_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[SaleItem] 상품명은 최대 31자 입니다."),
    SALE_ITEM_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[SaleItem] 상품 가격은 음수일 수 없습니다."),
    SALE_ITEM_QUANTITY_NON_POSITIVE(HttpStatus.BAD_REQUEST, "[SaleItem] 상품 수량은 0보다 커야 합니다."),
}
