package com.fzanutto.rinhabackend.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID

@Table(name = "person")
data class PersonEntity(
    @Id
    var id: UUID = UUID.randomUUID(),

    @Column(value = "nickname")
    var apelido: String = "",

    @Column("name")
    var nome: String = "",

    @Column("birthday")
    var nascimento: LocalDate = LocalDate.now(),

    @Column("stack")
    var stack: String? = null
) : Serializable {
    fun mapToDTO() = PersonDTO(
        id = id,
        apelido = apelido,
        nome = nome,
        nascimento = nascimento,
        stack = stack?.split("; ")
    )
}