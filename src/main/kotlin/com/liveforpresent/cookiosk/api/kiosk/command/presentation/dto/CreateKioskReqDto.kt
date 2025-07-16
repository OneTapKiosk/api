package com.liveforpresent.cookiosk.api.kiosk.command.presentation.dto

data class CreateKioskReqDto(
    val name: String,
    val location: String,
    val status: String,
    val version: String,
    val devices: Set<String>,
    val companyId: Long,
)
