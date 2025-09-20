package com.liveforpresent.onetapkiosk.ordering

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = ["com.liveforpresent.onetapkiosk.ordering", "com.liveforpresent.onetapkiosk.common"])
class OrderingApplication

fun main(args: Array<String>) {
	runApplication<OrderingApplication>(*args)
}
