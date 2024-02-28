package org.example.assignment.model

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.property.shouldBeNullable
import io.kotest.matchers.shouldBe
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.junit.jupiter.api.Test

class MovementTest {
    @Test
    fun `movement id should be sequence to allow ascending sort`() {
        Movement::class.java
            .getDeclaredField(Movement::id.name)
            .annotations.find { annotation -> annotation is GeneratedValue }
            .shouldNotBeNull()
            .let { it as GeneratedValue }
            .strategy shouldBe GenerationType.SEQUENCE
    }

    @Test
    fun `movement id should be nullable to allow user to send payloads without an id`() {
        Movement::id.shouldBeNullable()
    }

    @Test
    fun `direction values should be stored as Int based on the cardinal enum ordinal value`() {
        Movement::class.java
            .getDeclaredField(Movement::direction.name)
            .annotations.find { annotation -> annotation is Enumerated }
            .shouldNotBeNull()
            .let { it as Enumerated }
            .value shouldBe EnumType.ORDINAL
    }
}