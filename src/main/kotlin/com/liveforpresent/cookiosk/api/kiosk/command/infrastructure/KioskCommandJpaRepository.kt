package com.liveforpresent.cookiosk.api.kiosk.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface KioskCommandJpaRepository: JpaRepository<KioskEntity, Long>
