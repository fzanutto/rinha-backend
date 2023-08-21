package com.fzanutto.rinhabackend.controller

import com.fzanutto.rinhabackend.entity.Person
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(produces = ["application/json"])
class PersonController {

    @GetMapping("/pessoas/{id}")
    fun getPeople(@PathVariable id: UUID): ResponseEntity<Person> {
        return ResponseEntity(
            Person(id = UUID.randomUUID(), nome = "Teste"),
            HttpStatusCode.valueOf(200)
        )
    }
}