package org.example.assignment.model

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.property.shouldBeNullable
import io.kotest.matchers.shouldBe
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.junit.jupiter.api.Test

class RobotTest {
    @Test
    fun `robot id should be UUID`() {
        Robot::class.java
            .getDeclaredField(Robot::id.name)
            .annotations.find { annotation -> annotation is GeneratedValue }
            .shouldNotBeNull()
            .let { it as GeneratedValue }
            .strategy shouldBe GenerationType.UUID
    }

    @Test
    fun `robot id should be nullable to allow persisting without the need to generate the UUID at the controller layer`() {
        Robot::id.shouldBeNullable()
    }
}