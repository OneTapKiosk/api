package com.liveforpresent.onetapkiosk.common.exception

class CustomException (
    val code: CustomExceptionCode,
    val data: Any?
): RuntimeException()
