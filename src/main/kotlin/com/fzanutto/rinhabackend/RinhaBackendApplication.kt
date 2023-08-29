package com.fzanutto.rinhabackend

import com.fzanutto.rinhabackend.converter.ListToStringWriteConverter
import com.fzanutto.rinhabackend.converter.StringToListReadConverter
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableCaching
@EnableR2dbcRepositories
class RinhaBackendApplication

fun main(args: Array<String>) {
	runApplication<RinhaBackendApplication>(*args)
}

@Configuration
class Config(
	private val connectionFactory: ConnectionFactory
): AbstractR2dbcConfiguration() {
	override fun connectionFactory(): ConnectionFactory {
		return connectionFactory
	}

	override fun getCustomConverters(): MutableList<Any> {
		return mutableListOf(StringToListReadConverter(), ListToStringWriteConverter())
	}
}
