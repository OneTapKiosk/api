package com.liveforpresent.cookiosk.api.cart.command.application.handler

import com.liveforpresent.cookiosk.api.cart.command.application.command.CreateCartCommand
import com.liveforpresent.cookiosk.api.cart.command.domain.Cart
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRedisRepository
import com.liveforpresent.cookiosk.api.cart.command.domain.CartProps
import com.liveforpresent.cookiosk.api.cart.command.domain.vo.CartId
import com.liveforpresent.cookiosk.api.kiosk.command.domain.vo.KioskId
import com.liveforpresent.cookiosk.shared.core.domain.vo.Money
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CreateCartHandler(
    private val cartCommandRedisRepository: CartCommandRedisRepository
) {
    fun execute(command: CreateCartCommand) {
        val cartProps = CartProps(
            cartItems = mutableSetOf(),
            totalPrice = Money.create(0),
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            kioskId = KioskId(command.kioskId)
        )

        val cart = Cart.create(CartId(SnowflakeIdUtil.generateId()), cartProps)

        cartCommandRedisRepository.save(cart)
    }
}
