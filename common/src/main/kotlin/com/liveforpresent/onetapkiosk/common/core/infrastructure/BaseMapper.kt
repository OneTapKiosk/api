package com.liveforpresent.onetapkiosk.common.core.infrastructure

interface BaseMapper<D, P> {
    fun toDomain(persistence: P): D
    fun toPersistence(domain: D): P
}
