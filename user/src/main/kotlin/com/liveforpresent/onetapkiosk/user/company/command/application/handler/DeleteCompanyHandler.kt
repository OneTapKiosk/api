package com.liveforpresent.onetapkiosk.user.company.command.application.handler

import com.liveforpresent.onetapkiosk.user.company.command.application.command.DeleteCompanyCommand
import com.liveforpresent.onetapkiosk.user.company.command.domain.CompanyCommandRepository
import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
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
