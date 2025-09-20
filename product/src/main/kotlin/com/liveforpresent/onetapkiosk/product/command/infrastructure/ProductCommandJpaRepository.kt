package com.liveforpresent.onetapkiosk.product.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface ProductCommandJpaRepository: JpaRepository<ProductEntity, Long>
