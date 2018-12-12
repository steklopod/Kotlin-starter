import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include
import org.gradle.internal.impldep.org.junit.platform.launcher.EngineFilter.includeEngines

plugins {
    val kotlinVersion = "1.3.10"
    base
    java
    application
    id("org.springframework.boot") version "2.1.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion

}
application {
    mainClassName = "ru.Application.kt"
}

group = "ru.steklopod"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") { exclude(module = "spring-boot-starter-tomcat") }
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("com.h2database:h2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test") { exclude(module = "junit") }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    // Jackson Dependencies
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")

    runtime("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
    runtime("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    runtime("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtime("com.h2database:h2:1.4.197")
}


tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
    getByName<Test>("test") {
        useJUnitPlatform {
            includeEngines("junit-jupiter")
            excludeEngines("junit-vintage")
        }
    }
}