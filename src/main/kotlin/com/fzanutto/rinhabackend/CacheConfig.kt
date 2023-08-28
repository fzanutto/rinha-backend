package com.fzanutto.rinhabackend

import com.fzanutto.rinhabackend.model.PersonDTO
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class CacheConfig {

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, PersonDTO> {
        return RedisTemplate<String, PersonDTO>().apply {
            connectionFactory = redisConnectionFactory
        }
    }
}