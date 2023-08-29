package com.fzanutto.rinhabackend.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter

@ReadingConverter
class StringToListReadConverter: Converter<String, List<String>> {
    override fun convert(source: String): List<String> {
        return source.split("; ")
    }
}