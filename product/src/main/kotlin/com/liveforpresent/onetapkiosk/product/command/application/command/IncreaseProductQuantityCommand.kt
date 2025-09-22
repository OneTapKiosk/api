package com.liveforpresent.onetapkiosk.product.command.application.command

data class IncreaseProductQuantityCommand(
    val productId: String,
    val amount: Int
)
