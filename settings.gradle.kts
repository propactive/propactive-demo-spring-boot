rootProject.name = "assignment"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            val kotlin = version("kotlin", "1.9.22")
            val springBoot = version("springBoot", "3.2.3")
            val kotest = version("kotest", "5.8.0")
            val springDependencyManagement = version("springDependencyManagement", "1.1.4")
            val jacksonModuleKotlin = version("jacksonModuleKotlin", "2.16.1")
            val h2 = version("h2", "2.2.224")
            val restAssured = version("restAssured", "5.4.0")
            val propactive = version("propactive", "2.1.0")

            plugin("spring-boot", "org.springframework.boot").versionRef(springBoot)
            plugin("spring-dependency-management", "io.spring.dependency-management").versionRef(springDependencyManagement)
            plugin("jetbrains-kotlin-jvm", "org.jetbrains.kotlin.jvm").versionRef(kotlin)
            plugin("jetbrains-kotlin-spring", "org.jetbrains.kotlin.plugin.spring").versionRef(kotlin)
            plugin("jetbrains-kotlin-jpa", "org.jetbrains.kotlin.plugin.jpa").versionRef(kotlin)
            plugin("propactive", "io.github.propactive").versionRef(propactive)

            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").versionRef(kotlin)
            library("spring-boot-starter-data-jpa", "org.springframework.boot", "spring-boot-starter-data-jpa").versionRef(springBoot)
            library("spring-boot-starter-jdbc", "org.springframework.boot", "spring-boot-starter-jdbc").versionRef(springBoot)
            library("spring-boot-starter-web", "org.springframework.boot", "spring-boot-starter-web").versionRef(springBoot)
            library("spring-boot-starter-actuator", "org.springframework.boot", "spring-boot-starter-actuator").versionRef(springBoot)
            library("spring-boot-starter-test", "org.springframework.boot", "spring-boot-starter-test").versionRef(springBoot)
            library("jackson-module-kotlin", "com.fasterxml.jackson.module", "jackson-module-kotlin").versionRef(jacksonModuleKotlin)
            library("h2", "com.h2database", "h2").versionRef(h2)
            library("kotest-runner-junit5", "io.kotest", "kotest-runner-junit5").versionRef(kotest)
            library("kotest-assertions-core", "io.kotest", "kotest-assertions-core").versionRef(kotest)
            library("rest-assured", "io.rest-assured", "rest-assured").versionRef(restAssured)
            library("propactive-jvm", "io.github.propactive", "propactive-jvm").versionRef(propactive)
        }
    }
}