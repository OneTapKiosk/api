package com.liveforpresent.onetapkiosk.user.auth.command.presentation.dto.request

data class SignUpReqDto(
    val password: String,
    val companyId: String,
    val kioskId: String
)
