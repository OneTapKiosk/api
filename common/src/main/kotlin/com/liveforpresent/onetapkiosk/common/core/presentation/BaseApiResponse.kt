package com.liveforpresent.onetapkiosk.common.core.presentation

import org.springframework.http.HttpStatus

data class BaseApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val data: T? = null,
    val errorCode: HttpStatus? = null
)
