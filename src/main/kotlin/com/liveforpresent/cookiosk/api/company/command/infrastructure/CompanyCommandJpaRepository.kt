package com.liveforpresent.cookiosk.api.company.command.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface CompanyCommandJpaRepository: JpaRepository<CompanyEntity, Long>
