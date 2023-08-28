package com.fzanutto.rinhabackend.repository

import com.fzanutto.rinhabackend.model.PersonEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.UUID

@Repository
interface PersonRepository: CoroutineCrudRepository<PersonEntity, UUID> {
    @Query(
        value = "SELECT p.* FROM person p " +
            "WHERE " +
            "p.nome ILIKE '%' || :searchTerm || '%' " +
            "OR p.apelido ILIKE '%' || :searchTerm || '%' " +
            "OR p.stack ILIKE '%' || :searchTerm || '%' " +
            "LIMIT 50;"
    )
    suspend fun filterBySearch(searchTerm: String): List<PersonEntity>

    @Query(
        "INSERT INTO person " +
            "(id, nickname, name, birthday, stack) " +
            "VALUES " +
            "(:uuid, :nickname, :name, :birthday, :stack)"
    )
    suspend fun insertPerson(uuid: UUID, nickname: String, name: String, birthday: LocalDate, stack: String?)
}