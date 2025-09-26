package com.liveforpresent.onetapkiosk.common.core.domain

import com.liveforpresent.onetapkiosk.common.core.domain.vo.DomainEvent
import com.liveforpresent.onetapkiosk.common.core.domain.vo.Identifier

abstract class AggregateRoot<ID : Identifier<Long>>(id: ID): BaseEntity<ID>(id) {
    private val domainEvents = mutableListOf<DomainEvent>()

    fun addDomainEvent(event: DomainEvent) {
        domainEvents.add(event)
    }

    fun clearDomainEvents(): List<DomainEvent> {
        val events = domainEvents.toList()
        domainEvents.clear()
        return events
    }
}
