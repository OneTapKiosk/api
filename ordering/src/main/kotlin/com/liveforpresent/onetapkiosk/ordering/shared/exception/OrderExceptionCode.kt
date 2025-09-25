package com.liveforpresent.onetapkiosk.ordering.shared.exception

import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import org.springframework.http.HttpStatus

enum class OrderExceptionCode(
    override val code: HttpStatus,
    override val message: String
): CustomExceptionCode {
    ORDER_INVALID_STATE(HttpStatus.BAD_REQUEST, "[Order] 주문 상태가 유효하지 않습니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "[OrderRepository] 해당 주문이 존재하지 않습니다."),

    ORDER_ITEM_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[OrderItem] 상품명은 필수입니다."),
    ORDER_ITEM_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[OrderItem] 상품명은 최대 31자 입니다."),
    ORDER_ITEM_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[OrderItem] 상품 가격은 음수일 수 없습니다."),

    ORDER_STATUS_INVALID_STATE(HttpStatus.BAD_REQUEST, "[OrderStatus] 주문 상태가 유효하지 않습니다."),
}
