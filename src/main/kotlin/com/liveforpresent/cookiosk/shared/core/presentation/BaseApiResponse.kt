package com.liveforpresent.cookiosk.shared.core.presentation

data class BaseApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val data: T? = null,
    val errorCode: String? = null
)
