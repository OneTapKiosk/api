package com.liveforpresent.onetapkiosk.ordering.sale.query.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

@Entity
@Immutable
@Table(name = "vw_sale_by_item")
class SaleByItemView(
    @Id
    val name: String,
    val totalQuantity: Int,
    val totalPrice: Int
)
