package com.liveforpresent.onetapkiosk.common.config.security.jwt

enum class JwtTokenType(val value: String) {
    ACCESS_TOKEN("access_token"),
    REFRESH_TOKEN("refresh_token"),
}
