package org.example.assignment.service

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import java.util.stream.Stream
import kotlin.random.Random
import org.example.assignment.model.Coordinate
import org.example.assignment.model.Direction
import org.example.assignment.model.Movement
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(PER_CLASS)
class MovesServiceTest {
    @Test
    fun `empty coordinates returns empty movements`() {
        MovesService
            .derive(emptyList())
            .shouldBeEmpty()
    }

    @Test
    fun `should return an empty list when coordinates input single value as we assume it's the starting location`() {
        MovesService
            .derive(listOf(Coordinate(x = Random.nextInt(), y = Random.nextInt())))
            .shouldBeEmpty()
    }

    @Test
    fun `should translate positive x coordinates to EAST steps`() {
        val positiveInt = Random.nextInt(0, Int.MAX_VALUE)

        MovesService
            .derive(
                listOf(
                    Coordinate(x = 0, y = 0),
                    Coordinate(x = positiveInt, y = 0)
                )
            )
            .shouldContainExactly(
                Movement(direction = Direction.EAST, steps = positiveInt)
            )
    }

    @Test
    fun `should translate negative x coordinates to WEST steps`() {
        val negativeInt = Random.nextInt(Int.MIN_VALUE, 0)

        MovesService
            .derive(
                listOf(
                    Coordinate(x = 0, y = 0),
                    Coordinate(x = negativeInt, y = 0)
                )
            )
            .shouldContainExactly(
                Movement(direction = Direction.WEST, steps = -negativeInt)
            )
    }

    @Test
    fun `should translate positive y coordinates to NORTH steps`() {
        val positiveInt = Random.nextInt(0, Int.MAX_VALUE)

        MovesService
            .derive(
                listOf(
                    Coordinate(x = 0, y = 0),
                    Coordinate(x = 0, y = positiveInt)
                )
            )
            .shouldContainExactly(
                Movement(direction = Direction.NORTH, steps = positiveInt)
            )
    }

    @Test
    fun `should translate negative y coordinates to SOUTH steps`() {
        val negativeInt = Random.nextInt(Int.MIN_VALUE, 0)

        MovesService
            .derive(
                listOf(
                    Coordinate(x = 0, y = 0),
                    Coordinate(x = 0, y = negativeInt)
                )
            )
            .shouldContainExactly(
                Movement(direction = Direction.SOUTH, steps = -negativeInt)
            )
    }

    @ParameterizedTest
    @MethodSource("multipleCoordinatesArgs")
    fun `should translate multiple coordinates to movements`(coordinates: List<Coordinate>, expected: List<Movement>) {
        MovesService
            .derive(coordinates)
            .shouldContainExactly(expected)
    }

    private fun multipleCoordinatesArgs() = Stream.of(
        Arguments.of(
            listOf(
                Coordinate(x = 0, y = 0),
                Coordinate(x = 1, y = 0),
                Coordinate(x = 1, y = 1),
                Coordinate(x = 0, y = 1),
                Coordinate(x = 0, y = 0)
            ),
            listOf(
                Movement(direction = Direction.EAST, steps = 1),
                Movement(direction = Direction.NORTH, steps = 1),
                Movement(direction = Direction.WEST, steps = 1),
                Movement(direction = Direction.SOUTH, steps = 1)
            )
        ),
        Arguments.of(
            listOf(
                Coordinate(x = 0, y = 0),
                Coordinate(x = 4, y = 0),
                Coordinate(x = 2, y = 2),
                Coordinate(x = 0, y = 4)
            ),
            listOf(
                Movement(direction = Direction.EAST, steps = 4),
                Movement(direction = Direction.WEST, steps = 2),
                Movement(direction = Direction.NORTH, steps = 2),
                Movement(direction = Direction.WEST, steps = 2),
                Movement(direction = Direction.NORTH, steps = 2),
            )
        ),
        Arguments.of(
            listOf(
                Coordinate(x = 0, y = 0),
                Coordinate(x = 1, y = 0),
                Coordinate(x = 1, y = 3),
                Coordinate(x = 0, y = 3),
                Coordinate(x = 0, y = 0)
            ),
            listOf(
                Movement(direction = Direction.EAST, steps = 1),
                Movement(direction = Direction.NORTH, steps = 3),
                Movement(direction = Direction.WEST, steps = 1),
                Movement(direction = Direction.SOUTH, steps = 3)
            )
        )
    )
}