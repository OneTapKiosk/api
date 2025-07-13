package com.liveforpresent.cookiosk.api.sale.command.infrastructure

import com.liveforpresent.cookiosk.api.sale.command.domain.Sale
import com.liveforpresent.cookiosk.api.sale.command.domain.SaleCommandRepository
import org.springframework.stereotype.Repository

@Repository
class SaleCommandRepositoryImpl(
    private val saleCommandJpaRepository: SaleCommandJpaRepository
): SaleCommandRepository {
    override fun save(sale: Sale): Sale {
        val saleEntity = SaleEntity.toPersistence(sale)
        saleCommandJpaRepository.save(saleEntity)

        return sale
    }
}
