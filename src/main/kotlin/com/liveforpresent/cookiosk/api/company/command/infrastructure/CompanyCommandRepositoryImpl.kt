package com.liveforpresent.cookiosk.api.company.command.infrastructure

import com.liveforpresent.cookiosk.api.company.command.domain.Company
import com.liveforpresent.cookiosk.api.company.command.domain.CompanyCommandRepository
import com.liveforpresent.cookiosk.shared.exception.CustomException
import com.liveforpresent.cookiosk.shared.exception.CustomExceptionCode
import org.springframework.stereotype.Repository

@Repository
class CompanyCommandRepositoryImpl(
    private val companyCommandJpaRepository: CompanyCommandJpaRepository
): CompanyCommandRepository {
    override fun save(company: Company): Company {
        val companyEntity = CompanyEntity.toPersistence(company)
        companyCommandJpaRepository.save(companyEntity)

        return company
    }

    override fun findOne(id: Long): Company {
        val companyEntity = companyCommandJpaRepository.findById(id)
        .orElseThrow { throw CustomException(
            CustomExceptionCode.COMPANY_NOT_FOUND,
            "[CompanyCommandRepository] CompanyId: ${id}에 해당하는 사업체는 존재하지 않습니다."
        ) }

        return CompanyEntity.toDomain(companyEntity)
    }
}
