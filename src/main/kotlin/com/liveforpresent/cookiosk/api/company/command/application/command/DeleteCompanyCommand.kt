package com.liveforpresent.cookiosk.api.company.command.application.command

data class DeleteCompanyCommand(
    val id: String,
    val registrationNumber: String,
)
