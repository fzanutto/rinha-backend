package com.fzanutto.rinhabackend.repository

import com.fzanutto.rinhabackend.entity.PersonEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonRepository: CrudRepository<PersonEntity, UUID> {
    @Query(
        nativeQuery = true,
        value = "SELECT p.* FROM person p " +
            "WHERE LOWER(p.nickname) LIKE CONCAT('%', :searchTerm, '%') " +
            "OR LOWER(p.name) LIKE CONCAT('%', :searchTerm, '%') " +
            "OR LOWER(p.stack) LIKE CONCAT('%', :searchTerm, '%') " +
            "LIMIT 50;"
    )
    fun filterBySearch(searchTerm: String): List<PersonEntity>
}