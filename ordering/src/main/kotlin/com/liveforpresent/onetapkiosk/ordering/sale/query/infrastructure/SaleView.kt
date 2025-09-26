package com.liveforpresent.onetapkiosk.ordering.sale.query.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable
import java.time.Instant

@Entity
@Immutable
@Table(name = "vw_sale")
class SaleView(
    @Id
    val id: Long,
    val createdAt: Instant,
    val totalPrice: Int,
    val kioskId: Long
)
