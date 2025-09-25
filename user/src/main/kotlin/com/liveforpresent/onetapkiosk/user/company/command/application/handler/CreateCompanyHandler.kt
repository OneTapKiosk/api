package com.liveforpresent.onetapkiosk.user.company.command.application.handler

import com.liveforpresent.onetapkiosk.user.company.command.application.command.CreateCompanyCommand
import com.liveforpresent.onetapkiosk.user.company.command.application.dto.response.CreateCompanyResDto
import com.liveforpresent.onetapkiosk.user.company.command.domain.Company
import com.liveforpresent.onetapkiosk.user.company.command.domain.CompanyCommandRepository
import com.liveforpresent.onetapkiosk.user.company.command.domain.CompanyProps
import com.liveforpresent.onetapkiosk.user.company.command.domain.vo.RegistrationNumber
import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.common.core.infrastructure.util.SnowflakeIdUtil
import com.liveforpresent.onetapkiosk.common.exception.CustomException
import com.liveforpresent.onetapkiosk.user.shared.exception.CompanyExceptionCode
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
                CompanyExceptionCode.COMPANY_ALREADY_EXISTS,
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
