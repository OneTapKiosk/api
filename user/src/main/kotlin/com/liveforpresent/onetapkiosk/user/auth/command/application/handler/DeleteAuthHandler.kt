package com.liveforpresent.onetapkiosk.user.auth.command.application.handler

import com.liveforpresent.onetapkiosk.user.auth.command.application.command.DeleteAuthCommand
import com.liveforpresent.onetapkiosk.user.auth.command.domain.AuthCommandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteAuthHandler(
    private val authCommandRepository: AuthCommandRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun execute(command: DeleteAuthCommand) {
        val auth = authCommandRepository.findByKioskId(command.kioskId)

        val updatedAuth = auth.delete()

        authCommandRepository.save(updatedAuth)
    }
}
