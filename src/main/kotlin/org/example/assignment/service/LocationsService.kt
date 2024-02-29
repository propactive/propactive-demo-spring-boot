package org.example.assignment.service

import org.example.assignment.model.Coordinate
import org.example.assignment.model.Direction.EAST
import org.example.assignment.model.Direction.NORTH
import org.example.assignment.model.Direction.SOUTH
import org.example.assignment.model.Direction.WEST
import org.example.assignment.model.Movement

object LocationsService {

    @JvmStatic
    fun derive(movements: List<Movement>): List<Coordinate> =
        when {
            movements.isEmpty() -> Coordinate.initial()
            else -> movements.fold(Coordinate.initial()) { acc, movement ->
                acc.plus(
                    with(acc.last()) {
                        when (movement.direction) {
                            NORTH -> this.copy(y = y.plus(movement.steps))
                            SOUTH -> this.copy(y = y.minus(movement.steps))
                            EAST -> this.copy(x = x.plus(movement.steps))
                            WEST -> this.copy(x = x.minus(movement.steps))
                        }
                    }
                )
            }
        }
}