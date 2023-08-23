package com.fzanutto.rinhabackend

import com.fzanutto.rinhabackend.entity.PersonEntity
import com.fzanutto.rinhabackend.repository.PersonRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class PersonService(
    private val personRepository: PersonRepository
) {
    @Cacheable("person")
    fun getPersonById(uuid: UUID): PersonEntity? {
        return personRepository.findById(uuid).getOrNull()
    }
}