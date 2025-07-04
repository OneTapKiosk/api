package com.liveforpresent.cookiosk.shared.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun swagger(): OpenAPI {
        return OpenAPI()
            .info(info())
    }

    private fun info(): Info {
        return Info()
            .title("Earn Money Api")
            .description("Earn Money API")
            .version("1.0.0")
    }
}