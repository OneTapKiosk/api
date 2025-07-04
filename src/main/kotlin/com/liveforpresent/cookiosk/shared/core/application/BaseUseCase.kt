package com.liveforpresent.cookiosk.shared.core.application

interface BaseUseCase<I : BaseUseCaseInput, R> {
    fun execute(input: I): R
}
