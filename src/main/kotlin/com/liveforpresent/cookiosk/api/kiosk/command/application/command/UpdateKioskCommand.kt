package com.liveforpresent.cookiosk.api.kiosk.command.application.command

data class UpdateKioskCommand (
    val kioskId: Long,
    val name: String?,
    val location: String?,
    val status: String?,
    val version: String?,
    val devices: Set<String>?,
    val companyId: Long?,
)
