package com.liveforpresent.onetapkiosk.ordering.order.query.infrastructure.converter

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.liveforpresent.onetapkiosk.ordering.order.query.infrastructure.OrderItemView
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class OrderItemListConverter : AttributeConverter<List<OrderItemView>, String> {
    private val objectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(attribute: List<OrderItemView>?): String {
        return objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): List<OrderItemView> {
        return dbData?.let {
            objectMapper.readValue(it, object : TypeReference<List<OrderItemView>>() {})
        } ?: emptyList()
    }
}
