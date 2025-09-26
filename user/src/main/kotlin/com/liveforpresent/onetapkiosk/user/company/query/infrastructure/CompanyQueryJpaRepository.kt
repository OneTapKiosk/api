package com.liveforpresent.onetapkiosk.user.company.query.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface CompanyQueryJpaRepository: JpaRepository<CompanyView, Long>
