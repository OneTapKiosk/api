package com.liveforpresent.onetapkiosk.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Component

@SpringBootApplication
@ComponentScan(basePackages = ["com.liveforpresent.onetapkiosk.product", "com.liveforpresent.onetapkiosk.common"])
class ProductApplication

fun main(args: Array<String>) {
	runApplication<ProductApplication>(*args)
}
