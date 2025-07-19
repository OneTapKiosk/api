package com.liveforpresent.cookiosk.api.kiosk.query.domain

interface KioskModel {
    fun getName(): String
    fun getLocation(): String
    fun getStatus(): String
    fun getVersion(): String
    fun getDevices(): Set<String>
    fun getCompanyId(): Long
}
