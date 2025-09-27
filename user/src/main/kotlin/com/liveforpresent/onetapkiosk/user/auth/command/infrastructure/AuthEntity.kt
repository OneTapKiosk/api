package com.liveforpresent.onetapkiosk.user.auth.command.infrastructure

import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.AuthId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.user.auth.command.domain.Auth
import com.liveforpresent.onetapkiosk.user.auth.command.domain.AuthProps
import com.liveforpresent.onetapkiosk.user.auth.command.domain.vo.PasswordHash
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "auth")
class AuthEntity(
    @Id
    val id: Long,

    @Column(nullable = false)
    val passwordHash: String,

    @Column(nullable = false)
    val companyId: Long,

    @Column(nullable = false)
    val kioskId: Long,

    @Column(nullable = false)
    val createdAt: Instant,

    @Column(nullable = false)
    val updatedAt: Instant,

    @Column(nullable = false)
    val isDeleted: Boolean,

    @Column
    val deletedAt: Instant?,
) {
    companion object {
        fun toDomain(authEntity: AuthEntity): Auth {
            val props = AuthProps(
                passwordHash = PasswordHash.create(authEntity.passwordHash),
                companyId = CompanyId(authEntity.companyId),
                kioskId = KioskId(authEntity.kioskId),
                createdAt = authEntity.createdAt,
                updatedAt = authEntity.updatedAt,
                isDeleted = authEntity.isDeleted,
                deletedAt = authEntity.deletedAt
            )

            return Auth.create(AuthId(authEntity.id), props)
        }

        fun toPersistence(auth: Auth): AuthEntity {
            return AuthEntity(
                id = auth.id.value,
                passwordHash = auth.passwordHash.value,
                companyId = auth.companyId.value,
                kioskId = auth.kioskId.value,
                createdAt = auth.createdAt,
                updatedAt = auth.updatedAt,
                isDeleted = auth.isDeleted,
                deletedAt = auth.deletedAt
            )
        }
    }
}
