package com.liveforpresent.cookiosk.api.company.command.presentation.dto.request

data class CreateCompanyReqDto(
    val registrationNumber: String,
    val phone: String,
    val email: String
)
