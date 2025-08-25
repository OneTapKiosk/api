package com.liveforpresent.cookiosk.shared.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer

@TestConfiguration
@Profile("test")
class EmbeddedRedisConfig {
    private lateinit var redisServer: RedisServer

    @PostConstruct
    fun startRedis() {
        // 사용 가능한 포트를 찾아 Redis 서버 시작
        redisServer = RedisServer(6379)
        redisServer.start()
    }

    @PreDestroy
    fun stopRedis() {
        // 테스트 종료 시 Redis 서버 중지
        redisServer.stop()
    }
}
