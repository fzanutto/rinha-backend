package com.fzanutto.rinhabackend.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "person")
class PersonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID(),

    @Column(name = "nickname", unique = true, length = 32)
    @NotBlank
    var apelido: String = "",

    @Column(name = "name")
    @NotBlank
    var nome: String = "",

    @Column(name = "birthday")
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    var nascimento: LocalDate? = null,

    @Convert(converter = ListConverter::class)
    var stack: List<@NotBlank @Size(max = 32) String>? = null
)