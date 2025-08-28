package com.liveforpresent.cookiosk.api.kiosk.query.infrastructure.converter

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class KioskDevicesConverter: AttributeConverter<Set<String>, String> {
    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(attribute: Set<String>?): String? {
        if (attribute == null) {
            return null
        }
        return objectMapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): Set<String>? {
        if (dbData == null) {
            return null
        }
        val typeRef = object : TypeReference<Set<String>>() {}
        return objectMapper.readValue(dbData, typeRef)
    }
}
