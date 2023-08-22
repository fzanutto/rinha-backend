package com.fzanutto.rinhabackend.controller

import com.fzanutto.rinhabackend.entity.PersonEntity
import com.fzanutto.rinhabackend.repository.PersonRepository
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
class PersonController(
    private val personRepository: PersonRepository
) {
    @GetMapping("/pessoas/{id}")
    fun getPerson(@PathVariable id: UUID): ResponseEntity<PersonEntity> {
        return personRepository.findById(id)
            .map {
                ResponseEntity.ok(it)
            }
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/pessoas")
    fun postPerson(@RequestBody person: PersonEntity): ResponseEntity<Any> {
        val newPerson = personRepository.save(person)
        return ResponseEntity.created(URI.create("/pessoas/" + newPerson.id)).build()
    }

    @GetMapping("/pessoas")
    fun searchPerson(@RequestParam("t") t: String): ResponseEntity<List<PersonEntity>> {
        return ResponseEntity.ok(personRepository.filterBySearch(t.lowercase()))
    }

    @GetMapping("/contagem-pessoas")
    fun getPersonCount(): ResponseEntity<Long> {
        return ResponseEntity.ok(personRepository.count())
    }
}