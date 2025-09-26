package com.liveforpresent.onetapkiosk.user.kiosk.command.domain

interface KioskCommandRepository {
    fun save(kiosk: Kiosk): Kiosk
    fun findOne(id: Long): Kiosk
}
