package com.liveforpresent.onetapkiosk.user.kiosk.command.application.command

data class CreateKioskCommand(
    val name: String,
    val location: String,
    val status: String,
    val version: String,
    val devices: Set<String>,
    val companyId: Long,
)
