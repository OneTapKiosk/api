package com.liveforpresent.onetapkiosk.user.auth.command.application.command

data class SignUpCommand(
    val password: String,
    val companyId: Long,
    val kioskId: Long
)
