package com.fzanutto.rinhabackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class RinhaBackendApplication

fun main(args: Array<String>) {
	runApplication<RinhaBackendApplication>(*args)
}
