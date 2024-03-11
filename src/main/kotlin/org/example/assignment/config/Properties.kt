@file:Suppress("unused")

package org.example.assignment.config

import io.github.propactive.environment.Environment
import io.github.propactive.property.Property
import io.github.propactive.type.BOOLEAN
import io.github.propactive.type.CLASS
import io.github.propactive.type.PORT
import io.github.propactive.type.URI

@Environment
object Properties {
    @Property(["8080"], PORT::class)
    const val SPRING_SERVER_PORT = "server.port"

    /**
     * TIP:
     *   During development, switch to a file based H2 database as in-memory
     *   doesn't play nicely with Intellij built-in DB viewer: jdbc:h2:file:/tmp/assignment
     */
    @Property(["jdbc:h2:mem:test"], URI::class)
    const val SPRING_DATASOURCE_URL = "spring.datasource.url"

    @Property(["org.h2.Driver"], CLASS::class)
    const val SPRING_DATASOURCE_DRIVER_CLASS_NAME = "spring.datasource.driverClassName"

    @Property(["sa"])
    const val SPRING_DATASOURCE_USERNAME = "spring.datasource.username"

    @Property(["password"])
    const val SPRING_DATASOURCE_PASSWORD = "spring.datasource.password"

    /**
     * TIP:
     *   Accessing the H2 Console via: http://localhost:8080/h2
     */
    @Property(["true"], BOOLEAN::class)
    const val SPRING_H2_CONSOLE_ENABLED = "spring.h2.console.enabled"

    @Property(["/h2"], URI::class)
    const val SPRING_H2_CONSOLE_PATH = "spring.h2.console.path"

    @Property(["org.hibernate.dialect.H2Dialect"], CLASS::class)
    const val SPRING_JPA_DATABASE_PLATFORM = "spring.jpa.database-platform"

    @Property(["true"], BOOLEAN::class)
    const val SPRING_JPA_SHOW_SQL = "spring.jpa.show-sql"

    @Property(["create-drop"])
    const val SPRING_JPA_HIBERNATE_DDL_AUTO = "spring.jpa.hibernate.ddl-auto"
}