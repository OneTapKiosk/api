package com.liveforpresent.cookiosk.api.company.command.application.handler

import com.liveforpresent.cookiosk.api.company.command.application.command.DeleteCompanyCommand
import com.liveforpresent.cookiosk.api.company.command.domain.CompanyCommandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteCompanyHandler(
    private val companyCommandRepository: CompanyCommandRepository
) {
    @Transactional
    fun execute(command: DeleteCompanyCommand) {
        val company = companyCommandRepository.findOne(command.id.toLong())

        val updatedCompany = company.delete()

        companyCommandRepository.save(updatedCompany)
    }
}
