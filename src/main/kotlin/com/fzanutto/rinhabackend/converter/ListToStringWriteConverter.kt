package com.fzanutto.rinhabackend.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter

@WritingConverter
class ListToStringWriteConverter: Converter<List<String>, String> {
    override fun convert(source: List<String>): String? {
        return source.joinToString("; ")
    }
}