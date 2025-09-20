package com.liveforpresent.onetapkiosk.ordering.cart.query.infrastructure

import com.liveforpresent.onetapkiosk.ordering.cart.command.infrastructure.CartRedisEntity
import org.springframework.data.repository.CrudRepository

interface CartQueryCrudRepository: CrudRepository<CartRedisEntity, Long>