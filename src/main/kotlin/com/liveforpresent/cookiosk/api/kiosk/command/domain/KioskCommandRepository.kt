package com.liveforpresent.cookiosk.api.kiosk.command.domain

interface KioskCommandRepository {
    fun save(kiosk: Kiosk): Kiosk
}
