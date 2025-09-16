package com.liveforpresent.cookiosk.api.company.command.application.handler

import com.liveforpresent.cookiosk.api.company.command.application.command.CreateCompanyCommand
import com.liveforpresent.cookiosk.api.company.command.application.dto.response.CreateCompanyResDto
import com.liveforpresent.cookiosk.api.company.command.domain.Company
import com.liveforpresent.cookiosk.api.company.command.domain.CompanyCommandRepository
import com.liveforpresent.cookiosk.api.company.command.domain.CompanyProps
import com.liveforpresent.cookiosk.api.company.command.domain.vo.CompanyId
import com.liveforpresent.cookiosk.api.company.command.domain.vo.RegistrationNumber
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import com.liveforpresent.cookiosk.shared.exception.CustomException
import com.liveforpresent.cookiosk.shared.exception.CustomExceptionCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class CreateCompanyHandler(
    private val companyCommandRepository: CompanyCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: CreateCompanyCommand): CreateCompanyResDto {
        val existingCompany = companyCommandRepository.findByRegistrationNumber(command.registrationNumber)
        if (existingCompany != null) {
            if (!existingCompany.isDeleted) throw CustomException(
                CustomExceptionCode.COMPANY_ALREADY_EXISTS,
                "[Company] RegistrationNumber: ${command.registrationNumber}에 해당하는 사업자 등록번호로 이미 가입된 사업체가 있습니다."
            ) else {
                val updatedCompany = existingCompany.unDelete()

                companyCommandRepository.save(updatedCompany)

                eventPublisher.publish(updatedCompany)

                return CreateCompanyResDto(existingCompany.id.value.toString())
            }
        }

        val companyProps = CompanyProps(
            registrationNumber = RegistrationNumber.create(command.registrationNumber),
            phone = command.phone,
            email = command.email,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            isDeleted = false,
            deletedAt = null
        )

        val companyId = CompanyId(SnowflakeIdUtil.generateId())

        val company = Company.create(companyId, companyProps)

        companyCommandRepository.save(company)

        eventPublisher.publish(company)

        return CreateCompanyResDto(companyId.value.toString())
    }
}
