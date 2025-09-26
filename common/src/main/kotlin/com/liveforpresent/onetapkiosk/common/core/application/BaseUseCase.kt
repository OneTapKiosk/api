package com.liveforpresent.onetapkiosk.common.core.application

interface BaseUseCase<I : BaseUseCaseInput, R> {
    fun execute(input: I): R
}
