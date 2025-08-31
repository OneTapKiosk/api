package com.liveforpresent.cookiosk.shared.exception

class CustomException (
    val code: CustomExceptionCode,
    val data: Any?
): RuntimeException()
