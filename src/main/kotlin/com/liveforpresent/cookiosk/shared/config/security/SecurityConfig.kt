package com.liveforpresent.cookiosk.shared.config.security

import com.liveforpresent.cookiosk.shared.config.security.SecurityPathFilter.PUBLIC_PATHS
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }

        http.formLogin { it.disable() }

        http.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        http.cors {  }

        http.authorizeHttpRequests { req ->
            req.requestMatchers(*PUBLIC_PATHS).permitAll()

            req.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            req.anyRequest().authenticated()
        }

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}