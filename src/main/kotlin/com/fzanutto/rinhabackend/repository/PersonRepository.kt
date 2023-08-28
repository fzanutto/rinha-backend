package com.fzanutto.rinhabackend.repository

import com.fzanutto.rinhabackend.entity.PersonEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.time.LocalDate
import java.util.UUID

@Repository
interface PersonRepository: CoroutineCrudRepository<PersonEntity, UUID> {
    @Query(
        value = "SELECT p.* FROM person p " +
            "WHERE p.search ILIKE '%' || :searchTerm || '%' " +
            "LIMIT 50;"
    )
    suspend fun filterBySearch(searchTerm: String): List<PersonEntity>

    @Query(
        "INSERT INTO person " +
            "(id, nickname, name, birthday, stack, search) " +
            "VALUES " +
            "(:uuid, :nickname, :name, :birthday, :stack, :search)"
    )
    suspend fun insertPerson(uuid: UUID, nickname: String, name: String, birthday: LocalDate, stack: Array<String>?, search: String)
}