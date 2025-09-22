package com.liveforpresent.onetapkiosk.product.command.application.command

data class DecreaseProductQuantityCommand(
    val productId: String,
    val amount: Int
)
