package com.liveforpresent.cookiosk.api.cart.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface CartCommandJpaRepository: JpaRepository<CartEntity, Long>
