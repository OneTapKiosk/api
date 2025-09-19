package com.liveforpresent.cookiosk.api.company.command.application.command

data class DeleteCompanyCommand(
    val id: Long,
    val registrationNumber: String,
)
