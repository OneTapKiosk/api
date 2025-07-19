package com.liveforpresent.cookiosk.shared.config.security

import org.springframework.util.AntPathMatcher
import java.util.Arrays

object SecurityPathFilter {
    private val pathMatcher: AntPathMatcher = AntPathMatcher()

    val PUBLIC_PATHS = arrayOf(
        "/auth/**",
        "/api/**",
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/health/**",
        "/product/**",
        "/inventory/**",
        "/kiosk/**",
        "/order/**",
        "/sale/**",
        "/cart/**"
    )

    fun isPublicPath(path: String): Boolean {
        return Arrays.stream(PUBLIC_PATHS).anyMatch { pattern -> pathMatcher.match(pattern, path) }
    }
}