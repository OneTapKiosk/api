package com.liveforpresent.onetapkiosk.common.config.security.jwt

data class JwtTokenProperty(
    val secret: String,
    val expiration: Long
)
