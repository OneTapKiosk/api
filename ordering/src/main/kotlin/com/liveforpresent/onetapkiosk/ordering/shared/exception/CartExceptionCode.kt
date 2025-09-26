package com.liveforpresent.onetapkiosk.ordering.shared.exception

import com.liveforpresent.onetapkiosk.common.exception.CustomExceptionCode
import org.springframework.http.HttpStatus

enum class CartExceptionCode(
    override val code: HttpStatus,
    override val message: String
): CustomExceptionCode {
    CART_TOTAL_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[Cart] 총 가격은 음수일 수 없습니다."),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "[CartRepository] 해당 장바구니가 존재하지 않습니다."),

    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "[CartItem] 장바구니 내에 해당 상품이 존재하지 않습니다."),
    CART_ITEM_REMOVE_FAILURE(HttpStatus.CONFLICT, "[CartItem] 장바구니에서 상품 제거에 실패했습니다."),
    CART_ITEM_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[CartItem] 장바구니 내 상품명은 필수입니다."),
    CART_ITEM_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[CartItem] 장바구니 내 상품명은 최대 31자 입니다."),
    CART_ITEM_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[CartItem] 장바구니 내 상품 가격은 음수일 수 없습니다."),
    CART_ITEM_QUANTITY_NEGATIVE(HttpStatus.BAD_REQUEST, "[CartItem] 장바구니 내 상품 수량은 음수일 수 없습니다."),
    CART_ITEM_QUANTITY_NON_POSITIVE(HttpStatus.BAD_REQUEST, "[CartItem] 장바구니 네 감소시킬 상품 수량은 0보다 커야 합니다."),
}
