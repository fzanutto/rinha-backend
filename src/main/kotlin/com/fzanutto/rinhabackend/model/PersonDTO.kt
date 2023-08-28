package com.fzanutto.rinhabackend.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID

data class PersonDTO(
    var id: UUID = UUID.randomUUID(),
    var apelido: String = "",
    var nome: String = "",
    @JsonFormat(pattern="yyyy-MM-dd")
    var nascimento: LocalDate = LocalDate.now(),
    var stack: List<String>? = null
): Serializable {
    fun mapToEntity() = PersonEntity(
        id = id,
        apelido = apelido,
        nome = nome,
        nascimento = nascimento,
        stack = stack?.joinToString("; ")
    )
}
