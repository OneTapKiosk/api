package com.liveforpresent.onetapkiosk.user.company.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface CompanyCommandJpaRepository: JpaRepository<CompanyEntity, Long> {
    fun findByRegistrationNumber(registrationNumber: String): CompanyEntity?
}
