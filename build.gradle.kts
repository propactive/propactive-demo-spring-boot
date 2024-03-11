import org.gradle.api.JavaVersion.VERSION_21
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrains.kotlin.spring)
    alias(libs.plugins.jetbrains.kotlin.jpa)
    alias(libs.plugins.propactive)
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.reflect)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.jdbc)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.propactive.jvm)

    runtimeOnly(libs.h2)

    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.rest.assured)

    developmentOnly(libs.spring.boot.starter.actuator)
}

propactive {
    implementationClass = "org.example.assignment.config.Properties"
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = VERSION_21.toString()
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }

    wrapper {
        distributionType = ALL
    }
}
