package com.fzanutto.rinhabackend.entity

import jakarta.persistence.AttributeConverter

class ListConverter: AttributeConverter<List<String>, String>{
    override fun convertToDatabaseColumn(attribute: List<String>?): String? {
        return attribute?.joinToString(";")
    }

    override fun convertToEntityAttribute(dbData: String?): List<String>? {
        return dbData?.split(";")
    }
}