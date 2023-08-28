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
            "(:#{[0].id}, :#{[0].apelido}, :#{[0].nome}, :#{[0].nascimento}, :#{[0].stack}, :#{[0].search})"
    )
    suspend fun insertPerson(person: PersonEntity)
}