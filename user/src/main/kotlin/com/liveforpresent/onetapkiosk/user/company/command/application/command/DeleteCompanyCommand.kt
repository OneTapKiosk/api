package com.liveforpresent.onetapkiosk.user.company.command.application.command

data class DeleteCompanyCommand(
    val id: Long,
    val registrationNumber: String,
)
