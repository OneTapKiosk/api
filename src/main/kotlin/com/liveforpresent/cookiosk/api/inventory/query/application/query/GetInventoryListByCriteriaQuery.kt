package com.liveforpresent.cookiosk.api.inventory.query.application.query

data class GetInventoryListByCriteriaQuery(
    val isAvailable: Boolean?,
    val sortBy: String?
)
