package com.liveforpresent.cookiosk.api.company.command.application.handler

import com.liveforpresent.cookiosk.api.company.command.application.command.DeleteCompanyCommand
import com.liveforpresent.cookiosk.api.company.command.domain.CompanyCommandRepository
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteCompanyHandler(
    private val companyCommandRepository: CompanyCommandRepository,
    private val eventPublisher: DomainEventPublisher
) {
    @Transactional
    fun execute(command: DeleteCompanyCommand) {
        val company = companyCommandRepository.findOne(command.id)

        val updatedCompany = company.delete()

        companyCommandRepository.save(updatedCompany)

        eventPublisher.publish(updatedCompany)
    }
}
