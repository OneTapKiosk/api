package com.liveforpresent.onetapkiosk.common.core.domain

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Identifier
import java.io.Serializable

abstract class BaseEntity<ID : Identifier<Long>>(val id: ID) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as BaseEntity<*>
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}
