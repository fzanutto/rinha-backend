package com.fzanutto.rinhabackend.controller

import com.fzanutto.rinhabackend.entity.PersonEntity
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
    private val cache: RedisTemplate<String, PersonEntity>
) {
    @GetMapping("/pessoas/{id}")
    suspend fun getPerson(@PathVariable id: UUID): ResponseEntity<PersonEntity> {
        cache.opsForValue().get(id.toString())?.let {
            return ResponseEntity.ok(it)
        }

        return personRepository.findById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/pessoas")
    suspend fun postPerson(@Valid @RequestBody person: PersonEntity): ResponseEntity<Any> {
        if (cache.opsForValue().get(person.apelido) != null) {
            return ResponseEntity.unprocessableEntity().body("Nickname duplicado")
        }

        if (person.apelido.isBlank() || person.nome.isBlank()) {
            return ResponseEntity.unprocessableEntity().body("Apelido vazio")
        }

        cache.opsForValue().set(person.apelido, person)
        cache.opsForValue().set(person.id.toString(), person)

        personRepository.save(person)
        return ResponseEntity.created(URI.create("/pessoas/${person.uuid}")).build()
    }

    @GetMapping("/pessoas")
    suspend fun searchPerson(@RequestParam("t") searchTerm: String): ResponseEntity<List<PersonEntity>> {
        return personRepository.filterBySearch(searchTerm).let {
            ResponseEntity.ok(it)
        }
    }

    @GetMapping("/contagem-pessoas")
    suspend fun getPersonCount(): ResponseEntity<Long> {
        return personRepository.count().let {
            ResponseEntity.ok(it)
        }
    }
}