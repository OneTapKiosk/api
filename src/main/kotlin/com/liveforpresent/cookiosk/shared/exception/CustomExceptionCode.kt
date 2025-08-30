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
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "[ProductRepository] 해당 상품을 찾을 수 없습니다"),

    // Order
    ORDER_INVALID_STATE(HttpStatus.BAD_REQUEST, "[Order] 주문 상태가 유효하지 않습니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "[OrderRepository] 해당 주문이 존재하지 않습니다."),

    ORDER_ITEM_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[OrderItem] 상품명은 필수입니다."),
    ORDER_ITEM_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[OrderItem] 상품명은 최대 31자 입니다."),
    ORDER_ITEM_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[OrderItem] 상품 가격은 음수일 수 없습니다."),

    ORDER_STATUS_INVALID_STATE(HttpStatus.BAD_REQUEST, "[OrderStatus] 주문 상태가 유효하지 않습니다."),

    // Inventory
    INVENTORY_QUANTITY_NEGATIVE(HttpStatus.BAD_REQUEST, "[Inventory] 재고 수량은 음수일 수 없습니다."),
    INVENTORY_DECREASE_AMOUNT_NON_POSITIVE(HttpStatus.BAD_REQUEST, "[Inventory] 감소 수량은 0보다 커야 합니다."),
    INVENTORY_INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "[Inventory] 재고가 부족합니다."),

    INVENTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "[InventoryRepository] 해당 재고가 존재하지 않습니다."),
}
