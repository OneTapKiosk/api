plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

allprojects {
	group = "com.liveforpresent"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

	dependencies {
		// Swagger
		implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
		implementation("io.swagger.core.v3:swagger-annotations:2.2.31")

		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("org.springframework.boot:spring-boot-starter-security")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		compileOnly("org.projectlombok:lombok")
		developmentOnly("org.springframework.boot:spring-boot-devtools")
		runtimeOnly("com.mysql:mysql-connector-j")
		runtimeOnly("org.postgresql:postgresql")
		annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
		annotationProcessor("org.projectlombok:lombok")

		// log
		implementation("org.slf4j:slf4j-api:2.0.17")

		// test - kotest
		testImplementation("io.kotest:kotest-runner-junit5:6.0.0.M5")
		testImplementation("io.kotest:kotest-assertions-core:6.0.0.M5")
		testImplementation("io.kotest:kotest-property:6.0.0.M5")
		testImplementation("io.mockk:mockk:1.13.8")
		testImplementation("com.h2database:h2")
		testImplementation("it.ozimov:embedded-redis:0.7.3")

		// test
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testImplementation("org.springframework.security:spring-security-test")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}

	kotlin {
		compilerOptions {
			freeCompilerArgs.addAll("-Xjsr305=strict")
		}
	}

	allOpen {
		annotation("jakarta.persistence.Entity")
		annotation("jakarta.persistence.MappedSuperclass")
		annotation("jakarta.persistence.Embeddable")
	}

	java {
		toolchain {
			languageVersion = JavaLanguageVersion.of(21)
		}
	}

	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.getByName("bootJar") {
		enabled = false
	}

	tasks.getByName("jar") {
		enabled = true
	}
}

dependencies {
	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.31")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")

	// redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	// log
	implementation("org.slf4j:slf4j-api:2.0.17")

	// test - kotest
	testImplementation("io.kotest:kotest-runner-junit5:6.0.0.M5")
	testImplementation("io.kotest:kotest-assertions-core:6.0.0.M5")
	testImplementation("io.kotest:kotest-property:6.0.0.M5")
	testImplementation("io.mockk:mockk:1.13.8")
	testImplementation("com.h2database:h2")
	testImplementation("it.ozimov:embedded-redis:0.7.3")

	// test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
