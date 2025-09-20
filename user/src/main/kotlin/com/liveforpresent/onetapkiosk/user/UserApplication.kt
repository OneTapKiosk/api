package com.liveforpresent.onetapkiosk.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.liveforpresent.onetapkiosk.user", "com.liveforpresent.onetapkiosk.common"])
class UserApplication

fun main(args: Array<String>) {
	runApplication<UserApplication>(*args)
}
