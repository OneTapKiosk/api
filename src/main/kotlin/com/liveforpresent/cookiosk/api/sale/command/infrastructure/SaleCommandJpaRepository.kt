package com.liveforpresent.cookiosk.api.sale.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface SaleCommandJpaRepository: JpaRepository<SaleEntity, Long>
