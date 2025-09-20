package com.liveforpresent.onetapkiosk.ordering.sale.command.infrastructure

import com.liveforpresent.onetapkiosk.common.core.domain.vo.Money
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.entity.SaleItem
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.entity.SaleItemProps
import com.liveforpresent.onetapkiosk.ordering.sale.command.domain.vo.SaleItemId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "sale_item")
class SaleItemEntity(
    @Id
    @Column(nullable = false, unique = true)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val quantity: Int,

    @Column(nullable = false)
    val price: Int,
) {
    companion object {
        fun toPersistence(saleItem: SaleItem): SaleItemEntity {
            return SaleItemEntity(
                id = saleItem.id.value,
                name = saleItem.name,
                quantity = saleItem.quantity,
                price = saleItem.price.value
            )
        }

        fun toDomain(saleItemEntity: SaleItemEntity): SaleItem {
            val props = SaleItemProps(
                name = saleItemEntity.name,
                quantity = saleItemEntity.quantity,
                price = Money.create(saleItemEntity.price)
            )

            return SaleItem(SaleItemId(saleItemEntity.id), props)
        }
    }
}
