package com.liveforpresent.onetapkiosk.user.kiosk.query.domain

data class KioskModel(
    val name: String,
    val location: String,
    val status: String,
    val version: String,
    val devices: Set<String>,
    val companyId: String
)
