package org.example.assignment.repository

import io.kotest.matchers.shouldBe
import java.util.UUID
import kotlin.reflect.full.isSubclassOf
import org.example.assignment.model.Robot
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.data.jpa.repository.JpaRepository

class RobotRepositoryTest {
    @Test
    fun `should be JPA enabled`() {
        RobotRepository::class.isSubclassOf(JpaRepository::class)
    }

    @Test
    fun `should be a repository for Robot`() {
        RobotRepository::class.supertypes
            .find { it.classifier == JpaRepository::class }
            ?.arguments
            ?.also { (type, id) ->
                type.type?.classifier?.shouldBe(Robot::class) ?: fail("Repository Type should be ${Robot::class.simpleName}")
                id.type?.classifier?.shouldBe(UUID::class) ?: fail("Repository ID should be ${UUID::class.simpleName}")
            }
            ?: fail("${RobotRepository::class.simpleName} should be a repository for ${Robot::class.simpleName}")
    }
}