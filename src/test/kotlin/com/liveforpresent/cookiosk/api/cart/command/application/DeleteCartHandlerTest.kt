package com.liveforpresent.cookiosk.api.cart.command.application

import com.liveforpresent.cookiosk.api.cart.command.application.command.DeleteCartCommand
import com.liveforpresent.cookiosk.api.cart.command.application.handler.DeleteCartHandler
import com.liveforpresent.cookiosk.api.cart.command.domain.CartCommandRedisRepository
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify

class DeleteCartHandlerTest: DescribeSpec({
    val cartCommandRedisRepository: CartCommandRedisRepository = mockk<CartCommandRedisRepository>()
    val deleteCartHandler = DeleteCartHandler(cartCommandRedisRepository)
    mockkObject(SnowflakeIdUtil)

    describe("deleteCartHandler") {
        it("cartId를 받아 execute 내에서 해당 Cart를 삭제하는 메소드들을 호출한다.") {
            val cartId = 1L
            val command = DeleteCartCommand(cartId)

            every { cartCommandRedisRepository.deleteById(command.cartId) } returns Unit

            deleteCartHandler.execute(command)

            verify(exactly = 1) { cartCommandRedisRepository.deleteById(command.cartId) }
        }
    }
})
