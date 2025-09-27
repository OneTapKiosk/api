package com.liveforpresent.onetapkiosk.common.config.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val access: JwtTokenProperty,
    val refresh: JwtTokenProperty,
)
