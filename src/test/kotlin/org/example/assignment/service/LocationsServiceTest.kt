package org.example.assignment.service

import io.kotest.matchers.collections.shouldContainExactly
import java.util.stream.Stream
import org.example.assignment.model.Coordinate
import org.example.assignment.model.Direction
import org.example.assignment.model.Movement
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(PER_CLASS)
class LocationsServiceTest {
    @Test
    fun `empty movements returns initial coordinates`() {
        LocationsService
            .derive(emptyList())
            .shouldContainExactly(listOf(Coordinate(x = 0, y = 0)))
    }

    @ParameterizedTest
    @EnumSource(value = Direction::class)
    fun `assume that the initial location for the robot is always at (0, 0)`(direction: Direction) {
        LocationsService
            .derive(listOf(Movement(direction = direction, steps = 0)))
            .shouldContainExactly(listOf(Coordinate(x = 0, y = 0), Coordinate(x = 0, y = 0)))
    }

    @Test
    fun `should navigate towards positive x-axis when direction is EAST`() {
        LocationsService
            .derive(listOf(Movement(direction = Direction.EAST, steps = 1)))
            .shouldContainExactly(listOf(Coordinate(x = 0, y = 0), Coordinate(x = 1, y = 0)))
    }

    @Test
    fun `should navigate towards negative x-axis when direction is WEST`() {
        LocationsService
            .derive(listOf(Movement(direction = Direction.WEST, steps = 1)))
            .shouldContainExactly(listOf(Coordinate(x = 0, y = 0), Coordinate(x = -1, y = 0)))
    }

    @Test
    fun `should navigate towards positive y-axis when direction is NORTH`() {
        LocationsService
            .derive(listOf(Movement(direction = Direction.NORTH, steps = 1)))
            .shouldContainExactly(listOf(Coordinate(x = 0, y = 0), Coordinate(x = 0, y = 1)))
    }

    @Test
    fun `should navigate towards negative y-axis when direction is SOUTH`() {
        LocationsService
            .derive(listOf(Movement(direction = Direction.SOUTH, steps = 1)))
            .shouldContainExactly(listOf(Coordinate(x = 0, y = 0), Coordinate(x = 0, y = -1)))
    }

    @ParameterizedTest
    @MethodSource("accumulateMovementArgs")
    fun `should accumulate movement results`(movements: List<Movement>, coordinates: List<Coordinate>) {
        LocationsService
            .derive(movements)
            .shouldContainExactly(coordinates)
    }

    private fun accumulateMovementArgs() = Stream.of(
        Arguments.of(
            listOf(Movement(direction = Direction.SOUTH, steps = 1), Movement(direction = Direction.NORTH, steps = 1)),
            listOf(Coordinate(x = 0, y = 0), Coordinate(x = 0, y = -1), Coordinate(x = 0, y = 0))
        ),
        Arguments.of(
            listOf(Movement(direction = Direction.WEST, steps = 1), Movement(direction = Direction.EAST, steps = 1)),
            listOf(Coordinate(x = 0, y = 0), Coordinate(x = -1, y = 0), Coordinate(x = 0, y = 0))
        ),
        Arguments.of(
            listOf(Movement(direction = Direction.SOUTH, steps = 1), Movement(direction = Direction.NORTH, steps = 1), Movement(direction = Direction.WEST, steps = 1), Movement(direction = Direction.EAST, steps = 1)),
            listOf(Coordinate(x = 0, y = 0), Coordinate(x = 0, y = -1), Coordinate(x = 0, y = 0), Coordinate(x = -1, y = 0), Coordinate(x = 0, y = 0))
        ),
    )
}