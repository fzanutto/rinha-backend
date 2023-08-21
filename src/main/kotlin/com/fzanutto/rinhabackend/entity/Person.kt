package com.fzanutto.rinhabackend.entity

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.Date
import java.util.UUID

@Entity
@Table
class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID(),
    @Column(unique = true, length = 32)
    @NotBlank
    var apelido: String = "",
    @NotBlank
    var nome: String = "",
    var nascimento: Date = Date(),
    @Convert(converter = ListConverter::class)
    var stack: List<@Size(max = 32) String> = emptyList()
)