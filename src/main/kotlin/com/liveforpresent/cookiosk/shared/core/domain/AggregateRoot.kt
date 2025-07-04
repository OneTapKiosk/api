package com.liveforpresent.cookiosk.shared.core.domain

import com.liveforpresent.cookiosk.shared.core.domain.vo.DomainEvent
import com.liveforpresent.cookiosk.shared.core.domain.vo.Identifier

abstract class AggregateRoot<ID : Identifier<Long>>(id: ID): BaseEntity<ID>(id) {
    private val domainEvents = mutableListOf<DomainEvent>()

    fun addDomainEvent(event: DomainEvent) {
        domainEvents.add(event)
    }

    fun pullDomainEvents(): List<DomainEvent> {
        val events = domainEvents.toList()
        domainEvents.clear()
        return events
    }
}
