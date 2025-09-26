package com.liveforpresent.onetapkiosk.ordering.cart.command.infrastructure

import org.springframework.data.repository.CrudRepository

interface CartCommandCrudRepository: CrudRepository<CartRedisEntity, Long>
