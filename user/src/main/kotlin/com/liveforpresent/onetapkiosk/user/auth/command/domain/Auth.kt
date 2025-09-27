package com.liveforpresent.onetapkiosk.user.auth.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.AggregateRoot
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.AuthId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.user.auth.command.domain.vo.PasswordHash
import java.time.Instant

class Auth private constructor(
    id: AuthId,
    private val props: AuthProps
): AggregateRoot<AuthId>(id) {
    companion object {
        fun create(id: AuthId, props: AuthProps): Auth {
            val auth = Auth(id, props)
            auth.validate()

            return auth
        }
    }

    fun validate() {}

    fun update(
        newPassword: PasswordHash,
        newCompanyId: CompanyId,
        newKioskId: KioskId,
    ): Auth {
        val updatedAuth = Auth(id, props.copy(
            passwordHash = newPassword,
            companyId = newCompanyId,
            kioskId = newKioskId,
            updatedAt = Instant.now()
        ))

        updatedAuth.validate()

        return updatedAuth
    }

    fun delete(): Auth {
        val updatedAuth = Auth(id, props.copy(
            isDeleted = true,
            deletedAt = Instant.now()
        ))

        updatedAuth.validate()

        return updatedAuth
    }

    fun unDelete(): Auth {
        val updatedAuth = Auth(id, props.copy(
            isDeleted = false,
            deletedAt = null
        ))

        updatedAuth.validate()

        return updatedAuth
    }

    val passwordHash: PasswordHash get() = props.passwordHash
    val companyId: CompanyId get() = props.companyId
    val kioskId: KioskId get() = props.kioskId
    val createdAt: Instant get() = props.createdAt
    val updatedAt: Instant get() = props.updatedAt
    val isDeleted: Boolean get() = props.isDeleted
    val deletedAt: Instant? get() = props.deletedAt
}
