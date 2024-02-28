package org.example.assignment.model

import io.kotest.matchers.collections.shouldBeSingleton
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.property.shouldBeNullable
import io.kotest.matchers.shouldBe
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.junit.jupiter.api.Test

class CoordinatesTest {
    @Test
    fun `coordinate id should be sequence to allow ascending sort`() {
        Coordinate::class.java
            .getDeclaredField(Coordinate::id.name)
            .annotations.find { annotation -> annotation is GeneratedValue }
            .shouldNotBeNull()
            .let { it as GeneratedValue }
            .strategy shouldBe GenerationType.SEQUENCE
    }

    @Test
    fun `coordinates id should be nullable to allow user to send payloads without an id`() {
        Movement::id.shouldBeNullable()
    }

    @Test
    fun `initial coordinates should be set to (x = 0, y = 0)`() {
        Coordinate.initial().shouldBeSingleton().single().apply {
            x shouldBeExactly 0
            y shouldBeExactly 0
        }
    }
}