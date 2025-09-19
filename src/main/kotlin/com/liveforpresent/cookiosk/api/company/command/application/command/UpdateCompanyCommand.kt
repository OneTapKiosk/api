package com.liveforpresent.cookiosk.api.company.command.application.command

data class UpdateCompanyCommand(
    val id: Long,
    val newRegistrationNumber: String?,
    val newPhone: String?,
    val newEmail: String?,
)
