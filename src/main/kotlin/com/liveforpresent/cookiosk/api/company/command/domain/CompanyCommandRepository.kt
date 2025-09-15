package com.liveforpresent.cookiosk.api.company.command.domain

interface CompanyCommandRepository {
    fun save(company: Company): Company
    fun findOne(id: Long): Company
    fun findByRegistrationNumber(registrationNumber: String): Company?
}
