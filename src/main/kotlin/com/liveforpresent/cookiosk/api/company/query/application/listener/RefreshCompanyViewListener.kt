package com.liveforpresent.cookiosk.api.company.query.application.listener

import com.liveforpresent.cookiosk.api.company.command.domain.event.CompanyCreatedEvent
import com.liveforpresent.cookiosk.api.company.command.domain.event.CompanyDeletedEvent
import com.liveforpresent.cookiosk.api.company.command.domain.event.CompanyUpdatedEvent
import com.liveforpresent.cookiosk.api.company.query.application.handler.RefreshCompanyViewHandler
import com.liveforpresent.cookiosk.shared.core.domain.vo.DomainEvent
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
