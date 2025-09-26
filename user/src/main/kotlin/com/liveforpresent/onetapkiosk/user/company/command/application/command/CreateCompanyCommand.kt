package com.liveforpresent.onetapkiosk.user.company.command.application.command

data class CreateCompanyCommand(
    val registrationNumber: String,
    val phone: String,
    val email: String,
)
