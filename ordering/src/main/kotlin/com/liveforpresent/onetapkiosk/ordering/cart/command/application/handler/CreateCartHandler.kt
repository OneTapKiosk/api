package com.liveforpresent.onetapkiosk.ordering.cart.command.application.handler

import com.liveforpresent.onetapkiosk.ordering.cart.command.application.command.CreateCartCommand
import com.liveforpresent.onetapkiosk.ordering.cart.command.application.dto.CreateCartResDto
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.Cart
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.CartCommandRedisRepository
import com.liveforpresent.onetapkiosk.ordering.cart.command.domain.CartProps
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CartId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.infrastructure.util.SnowflakeIdUtil
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CreateCartHandler(
    private val cartCommandRedisRepository: CartCommandRedisRepository
) {
    fun execute(command: CreateCartCommand): CreateCartResDto {
        val cartId = CartId(SnowflakeIdUtil.generateId())
        val cartProps = CartProps(
            cartItems = mutableSetOf(),
            totalPrice = Money.create(0),
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            kioskId = KioskId(command.kioskId)
        )

        val cart = Cart.create(cartId, cartProps)

        cartCommandRedisRepository.save(cart)

        return CreateCartResDto(cartId.value.toString())
    }
}
