package com.liveforpresent.onetapkiosk.user.company.command.domain

import com.liveforpresent.onetapkiosk.user.company.command.domain.Company

interface CompanyCommandRepository {
    fun save(company: Company): Company
    fun findOne(id: Long): Company
    fun findByRegistrationNumber(registrationNumber: String): Company?
}
