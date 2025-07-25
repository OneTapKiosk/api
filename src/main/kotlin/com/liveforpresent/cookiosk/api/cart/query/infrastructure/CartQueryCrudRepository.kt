package com.liveforpresent.cookiosk.api.cart.query.infrastructure

import com.liveforpresent.cookiosk.api.cart.command.infrastructure.CartRedisEntity
import org.springframework.data.repository.CrudRepository

interface CartQueryCrudRepository: CrudRepository<CartRedisEntity, Long>