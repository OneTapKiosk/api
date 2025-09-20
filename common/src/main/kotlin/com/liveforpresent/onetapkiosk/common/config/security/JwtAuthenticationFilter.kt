package com.liveforpresent.onetapkiosk.common.config.security

import com.liveforpresent.onetapkiosk.common.config.security.SecurityPathFilter.isPublicPath
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val path = request.requestURI

        if (isPublicPath(path)) {
            filterChain.doFilter(request, response)
            return
        }
    }
}