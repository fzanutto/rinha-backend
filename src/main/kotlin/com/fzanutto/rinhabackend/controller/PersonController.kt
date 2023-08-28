package com.fzanutto.rinhabackend.controller

import com.fzanutto.rinhabackend.model.PersonDTO
import com.fzanutto.rinhabackend.repository.PersonRepository
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheConfig
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping(produces = ["application/json"])
@CacheConfig(cacheNames = ["person"])
class PersonController(
    private val personRepository: PersonRepository,
    private val cache: RedisTemplate<String, PersonDTO>
) {
    @GetMapping("/pessoas/{id}")
    suspend fun getPerson(@PathVariable id: UUID): ResponseEntity<PersonDTO> {
        cache.opsForValue().get(id.toString())?.let {
            return ResponseEntity.ok(it)
        }

        return personRepository.findById(id)?.let {
            ResponseEntity.ok(it.mapToDTO())
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/pessoas")
    suspend fun postPerson(@Valid @RequestBody person: PersonDTO): ResponseEntity<String> {
        if (cache.opsForValue().get(person.apelido) != null) {
            return ResponseEntity.unprocessableEntity().body("Nickname duplicado")
        }

        if (person.apelido.isBlank() || person.nome.isBlank()) {
            return ResponseEntity.unprocessableEntity().body("Apelido vazio")
        }

        cache.opsForValue().set(person.apelido, person)
        cache.opsForValue().set(person.id.toString(), person)

        val entity = person.mapToEntity()

        personRepository.insertPerson(
            uuid = entity.id,
            name = entity.nome,
            nickname = entity.apelido,
            birthday = entity.nascimento,
            stack = entity.stack
        )

        return ResponseEntity.created(URI.create("/pessoas/${person.id}")).build()
    }

    @GetMapping("/pessoas")
    suspend fun searchPerson(@RequestParam("t") searchTerm: String): ResponseEntity<List<PersonDTO>> {
        return personRepository.filterBySearch(searchTerm).let {
            ResponseEntity.ok(it.map { it.mapToDTO() })
        }
    }

    @GetMapping("/contagem-pessoas")
    suspend fun getPersonCount(): ResponseEntity<Long> {
        return personRepository.count().let {
            ResponseEntity.ok(it)
        }
    }
}