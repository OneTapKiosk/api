package com.liveforpresent.onetapkiosk.common.exception

import org.springframework.http.HttpStatus

interface CustomExceptionCode {
    val code: HttpStatus
    val message: String
}
