package com.liveforpresent.cookiosk.api.company.query.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface CompanyQueryJpaRepository: JpaRepository<CompanyView, Long>
