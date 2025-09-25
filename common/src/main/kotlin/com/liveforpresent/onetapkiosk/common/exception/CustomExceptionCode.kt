package com.liveforpresent.onetapkiosk.common.exception

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
    PRODUCT_QUANTITY_NEGATIVE(HttpStatus.BAD_REQUEST, "[Product] 재고 수량은 음수일 수 없습니다."),
    PRODUCT_DECREASE_AMOUNT_NON_POSITIVE(HttpStatus.BAD_REQUEST, "[Product] 감소 수량은 0보다 커야 합니다."),
    PRODUCT_INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "[Product] 재고가 부족합니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "[ProductRepository] 해당 상품을 찾을 수 없습니다"),

    // Order
    ORDER_INVALID_STATE(HttpStatus.BAD_REQUEST, "[Order] 주문 상태가 유효하지 않습니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "[OrderRepository] 해당 주문이 존재하지 않습니다."),

    ORDER_ITEM_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[OrderItem] 상품명은 필수입니다."),
    ORDER_ITEM_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[OrderItem] 상품명은 최대 31자 입니다."),
    ORDER_ITEM_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[OrderItem] 상품 가격은 음수일 수 없습니다."),

    ORDER_STATUS_INVALID_STATE(HttpStatus.BAD_REQUEST, "[OrderStatus] 주문 상태가 유효하지 않습니다."),

    // Inventory
    INVENTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "[InventoryRepository] 해당 재고가 존재하지 않습니다."),

    // Kiosk
    KIOSK_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[Kiosk] 키오스크명은 필수 입니다."),
    KIOSK_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[Kiosk] 키오스크명은 최대 31자 입니다."),
    KIOSK_NOT_FOUND(HttpStatus.NOT_FOUND, "[KioskRepository] 해당 키오스크가 존재하지 않습니다."),

    KIOSK_DEVICE_INVALID_DEVICE(HttpStatus.BAD_REQUEST, "[KioskDevice] 유효하지 않은 키오스크 주변 장치입니다."),

    KIOSK_STATUS_INVALID_STATUS(HttpStatus.BAD_REQUEST, "[KioskStatus] 유효하지 않은 키오스크 상태 입니다."),

    // Sale
    SALE_TOTAL_PRICE_NON_POSITIVE(HttpStatus.BAD_REQUEST, "[Sale] 총 가격은 0보다 커야 합니다."),

    SALE_ITEM_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[SaleItem] 상품명은 필수입니다."),
    SALE_ITEM_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[SaleItem] 상품명은 최대 31자 입니다."),
    SALE_ITEM_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[SaleItem] 상품 가격은 음수일 수 없습니다."),
    SALE_ITEM_QUANTITY_NON_POSITIVE(HttpStatus.BAD_REQUEST, "[SaleItem] 상품 수량은 0보다 커야 합니다."),

    // Cart
    CART_TOTAL_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[Cart] 총 가격은 음수일 수 없습니다."),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "[CartRepository] 해당 장바구니가 존재하지 않습니다."),

    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "[CartItem] 장바구니 내에 해당 상품이 존재하지 않습니다."),
    CART_ITEM_REMOVE_FAILURE(HttpStatus.CONFLICT, "[CartItem] 장바구니에서 상품 제거에 실패했습니다."),
    CART_ITEM_NAME_EMPTY(HttpStatus.BAD_REQUEST, "[CartItem] 장바구니 내 상품명은 필수입니다."),
    CART_ITEM_NAME_LENGTH_EXCEEDED(HttpStatus.BAD_REQUEST, "[CartItem] 장바구니 내 상품명은 최대 31자 입니다."),
    CART_ITEM_PRICE_NEGATIVE(HttpStatus.BAD_REQUEST, "[CartItem] 장바구니 내 상품 가격은 음수일 수 없습니다."),
    CART_ITEM_QUANTITY_NEGATIVE(HttpStatus.BAD_REQUEST, "[CartItem] 장바구니 내 상품 수량은 음수일 수 없습니다."),
    CART_ITEM_QUANTITY_NON_POSITIVE(HttpStatus.BAD_REQUEST, "[CartItem] 장바구니 네 감소시킬 상품 수량은 0보다 커야 합니다."),

    // Company
    COMPANY_REGISTRATION_NUMBER_EMPTY(HttpStatus.BAD_REQUEST, "[CompanyRegistrationNumber] 사업자등록번호는 필수 입니다."),
    COMPANY_INVALID_REGISTRATION_NUMBER(HttpStatus.BAD_REQUEST, "[CompanyRegistrationNumber] 유효하지 않은 사업자등록번호 형식입니다."),
    COMPANY_INVALID_STATUS(HttpStatus.BAD_REQUEST, "[CompanyStatus] 유효하지 않은 사업자 상태 입니다."),
    COMPANY_INVALID_EMAIL(HttpStatus.BAD_REQUEST, "[Company] 유효하지 않은 이메일 입니다."),
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "[CompanyRepository] 해당 사업체는 존재하지 않습니다."),
    COMPANY_ALREADY_EXISTS(HttpStatus.CONFLICT, "[Company] 해당 사업자 등록번호로 이미 가입된 사업체가 있습니다.")
}
