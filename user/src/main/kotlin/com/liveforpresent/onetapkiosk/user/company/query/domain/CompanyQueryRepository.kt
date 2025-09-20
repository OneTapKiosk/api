package com.liveforpresent.onetapkiosk.user.company.query.domain

import com.liveforpresent.onetapkiosk.user.company.query.domain.CompanyModel

interface CompanyQueryRepository {
    fun findById(id: Long): CompanyModel
    fun findAll(): List<CompanyModel>
    fun refreshView()
}
