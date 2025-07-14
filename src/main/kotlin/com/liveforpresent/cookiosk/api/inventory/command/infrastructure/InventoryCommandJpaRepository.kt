package com.liveforpresent.cookiosk.api.inventory.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface InventoryCommandJpaRepository: JpaRepository<InventoryEntity, Long>
