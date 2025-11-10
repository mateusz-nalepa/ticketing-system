plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.nalepa.ticketing"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2025.0.0")
    }
}

val micrometerDocsVersion by extra("1.0.2")

val adoc by configurations.creating


dependencies {
    // docs for observationDocumentation
    adoc("io.micrometer:micrometer-docs-generator:$micrometerDocsVersion")

    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("de.codecentric:spring-boot-admin-starter-client:3.5.5")

    // loki
    implementation("com.github.loki4j:loki-logback-appender:2.0.1")

    // tempo, spany, tracy
    implementation("io.micrometer:micrometer-tracing:1.5.5")
    implementation("io.micrometer:micrometer-tracing-bridge-brave:1.5.5")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave:3.5.1")

    // metrics
    implementation("io.micrometer:micrometer-registry-prometheus:1.15.5")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// files are created, but right now without content :pepe:
tasks.register<JavaExec>("generateObservabilityDocs") {
    mainClass.set("io.micrometer.docs.DocsGeneratorCommand")
    classpath = adoc
    args(
        rootDir.absolutePath,
        ".*",
        project.layout.buildDirectory.asFile.get().absolutePath + "/metrics"
    )
}
