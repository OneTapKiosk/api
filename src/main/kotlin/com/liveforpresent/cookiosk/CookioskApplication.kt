package com.liveforpresent.cookiosk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CookioskApplication

fun main(args: Array<String>) {
	runApplication<CookioskApplication>(*args)
}
