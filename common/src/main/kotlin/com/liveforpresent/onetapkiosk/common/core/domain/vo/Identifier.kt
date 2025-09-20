package com.liveforpresent.onetapkiosk.common.core.domain.vo

import java.io.Serializable

abstract class Identifier<T : Serializable>(val value: T) : Serializable {
    override fun equals(other: Any?): Boolean =
        this === other || (other is Identifier<*> && this.value == other.value)

    override fun hashCode(): Int = value.hashCode()

    override fun toString(): String = value.toString()
}
