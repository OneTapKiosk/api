package com.liveforpresent.cookiosk.api.cart.command.infrastructure

import org.springframework.data.repository.CrudRepository

interface CartCommandCrudRepository: CrudRepository<CartRedisEntity, Long>
