package com.liveforpresent.onetapkiosk.user.company.query.infrastructure

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.user.company.query.domain.CompanyModel
import com.liveforpresent.onetapkiosk.user.company.query.domain.CompanyQueryRepository
import com.liveforpresent.onetapkiosk.user.shared.exception.CompanyExceptionCode
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class CompanyQueryRepositoryImpl(
    private val companyQueryJpaRepository: CompanyQueryJpaRepository,
    private val em: EntityManager
): CompanyQueryRepository {
    override fun findById(id: Long): CompanyModel {
        val companyView = companyQueryJpaRepository.findById(id)
            .orElseThrow { CustomException(
                CompanyExceptionCode.COMPANY_NOT_FOUND,
                "[CompanyQueryRepository] CompanyId: ${id}에 해당하는 사업체는 존재하지 않습니다."
            ) }

        return CompanyModel(
            id = companyView.id.toString(),
            registrationNumber = companyView.registrationNumber,
            phone = companyView.phone,
            email = companyView.email,
            createdAt = companyView.createdAt,
            updatedAt = companyView.updatedAt
        )
    }

    override fun findAll(): List<CompanyModel> {
        val companyViews = companyQueryJpaRepository.findAll().toList()

        return companyViews.map { CompanyModel(
            it.id.toString(),
            registrationNumber = it.registrationNumber,
            phone = it.phone,
            email = it.email,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        ) }
    }

    override fun refreshView() {
        em.createNativeQuery("REFRESH MATERIALIZED VIEW CONCURRENTLY vw_company")
            .executeUpdate()
    }
}
