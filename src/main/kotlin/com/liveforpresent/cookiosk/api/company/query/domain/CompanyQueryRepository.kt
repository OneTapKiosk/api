package com.liveforpresent.cookiosk.api.company.query.domain

interface CompanyQueryRepository {
    fun findById(id: Long): CompanyModel
    fun findAll(): List<CompanyModel>
    fun refreshView()
}
