package com.liveforpresent.cookiosk.api.company.command.application.handler

import com.liveforpresent.cookiosk.api.company.command.application.command.UpdateCompanyCommand
import com.liveforpresent.cookiosk.api.company.command.domain.CompanyCommandRepository
import com.liveforpresent.cookiosk.api.company.command.domain.vo.RegistrationNumber
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateCompanyHandler(
    private val companyCommandRepository: CompanyCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: UpdateCompanyCommand) {
        val company = companyCommandRepository.findOne(command.id)

        val updatedCompany = company.update(
            newRegistrationNumber = command.newRegistrationNumber?.let{ RegistrationNumber.create(it) } ?: company.registrationNumber,
            newEmail = command.newEmail ?: company.email,
            newPhone = command.newPhone ?: company.phone
        )

        companyCommandRepository.save(updatedCompany)

        eventPublisher.publish(updatedCompany)
    }
}
