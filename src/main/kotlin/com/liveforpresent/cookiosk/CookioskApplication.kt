package com.liveforpresent.cookiosk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class CookioskApplication

fun main(args: Array<String>) {
	runApplication<CookioskApplication>(*args)
}
