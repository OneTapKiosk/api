package com.liveforpresent.onetapkiosk.ordering.sale.command.infrastructure

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.KioskId
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.SaleId
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.Sale
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.SaleProps
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "sale")
class SaleEntity(
    @Id
    @Column(nullable = false, unique = true)
    val id: Long,

    @Column(nullable = false)
    val totalPrice: Int,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "sale_id")
    val saleItems: MutableList<SaleItemEntity>,

    @Column(nullable = false)
    val kioskId: Long,

    @Column(nullable = false)
    val createdAt: Instant
) {
    companion object {
        fun toPersistence(sale: Sale): SaleEntity {
            return SaleEntity(
                id = sale.id.value,
                totalPrice = sale.totalPrice.value,
                saleItems = sale.saleItems.map { saleItem -> SaleItemEntity.toPersistence(saleItem) }.toMutableList(),
                kioskId = sale.kioskId.value,
                createdAt = sale.createdAt
            )
        }

        fun toDomain(saleEntity: SaleEntity): Sale {
            val props = SaleProps(
                totalPrice = Money.create(saleEntity.totalPrice),
                saleItems = saleEntity.saleItems.map { saleItem -> SaleItemEntity.toDomain(saleItem) }.toMutableList(),
                kioskId = KioskId(saleEntity.kioskId),
                createdAt = saleEntity.createdAt
            )

            return Sale.create(SaleId(saleEntity.id), props)
        }
    }
}
