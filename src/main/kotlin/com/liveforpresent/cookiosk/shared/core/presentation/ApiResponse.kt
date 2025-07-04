package com.liveforpresent.cookiosk.shared.core.presentation

data class ApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val data: T? = null,
    val errorCode: String? = null
)
