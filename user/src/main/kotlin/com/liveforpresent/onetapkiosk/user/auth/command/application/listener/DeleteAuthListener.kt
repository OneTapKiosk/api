package com.liveforpresent.onetapkiosk.user.auth.command.application.listener

import com.liveforpresent.onetapkiosk.user.auth.command.application.command.DeleteAuthCommand
import com.liveforpresent.onetapkiosk.user.auth.command.application.handler.DeleteAuthHandler
import com.liveforpresent.onetapkiosk.user.kiosk.command.domain.event.KioskDeletedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class DeleteAuthListener(
    private val deleteAuthHandler: DeleteAuthHandler
) {
    @TransactionalEventListener
    fun handle(event: KioskDeletedEvent) {
        val command = DeleteAuthCommand(event.kioskId)

        deleteAuthHandler.execute(command)
    }
}
