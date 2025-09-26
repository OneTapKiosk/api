package com.liveforpresent.onetapkiosk.user.company.command.infrastructure

import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.user.company.command.domain.Company
import com.liveforpresent.onetapkiosk.user.company.command.domain.CompanyCommandRepository
import com.liveforpresent.onetapkiosk.user.shared.exception.CompanyExceptionCode
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
            CompanyExceptionCode.COMPANY_NOT_FOUND,
            "[CompanyCommandRepository] CompanyId: ${id}에 해당하는 사업체는 존재하지 않습니다."
        ) }

        return CompanyEntity.toDomain(companyEntity)
    }

    override fun findByRegistrationNumber(registrationNumber: String): Company? {
        val companyEntity = companyCommandJpaRepository.findByRegistrationNumber(registrationNumber) ?: return null

        return CompanyEntity.toDomain(companyEntity)
    }
}
