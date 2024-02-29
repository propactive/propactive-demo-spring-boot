package org.example.assignment.service

import org.example.assignment.model.Coordinate
import org.example.assignment.model.Movement

object MovesService {
    fun derive(coordinates: List<Coordinate>): List<Movement> =
        when {
            coordinates.size <= 1 -> emptyList()
            else -> TODO("Short on time and sleepy as it's 1 am, apologies :)")
        }
}