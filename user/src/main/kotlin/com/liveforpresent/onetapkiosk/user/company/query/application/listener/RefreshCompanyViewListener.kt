package com.liveforpresent.onetapkiosk.user.company.query.application.listener

import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent
import com.liveforpresent.onetapkiosk.user.company.command.domain.event.CompanyCreatedEvent
import com.liveforpresent.onetapkiosk.user.company.command.domain.event.CompanyDeletedEvent
import com.liveforpresent.onetapkiosk.user.company.command.domain.event.CompanyUpdatedEvent
import com.liveforpresent.onetapkiosk.user.company.query.application.handler.RefreshCompanyViewHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class RefreshCompanyViewListener(
    private val refreshCompanyViewHandler: RefreshCompanyViewHandler
) {
    @TransactionalEventListener(
        classes = [
            CompanyCreatedEvent::class,
            CompanyUpdatedEvent::class,
            CompanyDeletedEvent::class
        ]
    )
    fun handle(event: DomainEvent) {
        refreshCompanyViewHandler.execute()
    }
}
