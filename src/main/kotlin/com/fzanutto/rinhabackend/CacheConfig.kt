package com.fzanutto.rinhabackend

import com.fzanutto.rinhabackend.entity.PersonEntity
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class CacheConfig {

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, PersonEntity> {
        return RedisTemplate<String, PersonEntity>().apply {
            connectionFactory = redisConnectionFactory
        }
    }
}