package com.liveforpresent.cookiosk.api.product.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface ProductCommandJpaRepository: JpaRepository<ProductEntity, Long>
