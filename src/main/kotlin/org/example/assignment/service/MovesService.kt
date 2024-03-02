package org.example.assignment.service

import kotlin.math.abs
import org.example.assignment.model.Coordinate
import org.example.assignment.model.Direction
import org.example.assignment.model.Direction.EAST
import org.example.assignment.model.Direction.NORTH
import org.example.assignment.model.Direction.SOUTH
import org.example.assignment.model.Direction.WEST
import org.example.assignment.model.Movement
import org.springframework.stereotype.Service

@Service
object MovesService {
    fun derive(coordinates: List<Coordinate>): List<Movement> = when {
        coordinates.size <= 1 -> emptyList()
        else -> coordinates
            .windowed(2, 1)
            .fold(emptyList<Movement?>()) { acc, (previous, next) ->
                acc
                    .plus(calculate(previous.x, next.x, EAST, WEST))
                    .plus(calculate(previous.y, next.y, NORTH, SOUTH))
            }
            .filterNotNull()
    }

    private fun calculate(
        start: Int,
        end: Int,
        positive: Direction,
        negative: Direction,
    ) = with(end - start) {
        when {
            this > 0 -> Movement(direction = positive, steps = this)
            this < 0 -> Movement(direction = negative, steps = abs(this))
            else -> null
        }
    }
}