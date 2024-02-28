package org.example.assignment.model

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.example.assignment.model.Direction.EAST
import org.example.assignment.model.Direction.NORTH
import org.example.assignment.model.Direction.SOUTH
import org.example.assignment.model.Direction.WEST
import org.junit.jupiter.api.Test

class DirectionTest {
    @Test
    fun `directions names are cardinal values`() {
        Direction
            .entries
            .map(Direction::name)
            .shouldContainExactly(setOf("NORTH", "EAST", "SOUTH", "WEST"))
    }

    @Test
    @Suppress("KotlinConstantConditions")
    fun `directions ordinal order is clockwise so we can optimise database storage via ordinal values`() {
        Direction.entries.forEach { direction: Direction ->
            when (direction) {
                NORTH -> direction.ordinal shouldBe 0
                EAST -> direction.ordinal shouldBe 1
                SOUTH -> direction.ordinal shouldBe 2
                WEST -> direction.ordinal shouldBe 3
            }
        }
    }
}
