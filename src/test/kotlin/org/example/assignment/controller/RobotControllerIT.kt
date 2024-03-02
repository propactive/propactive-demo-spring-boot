package org.example.assignment.controller

import io.restassured.RestAssured
import io.restassured.http.ContentType.JSON
import org.example.assignment.model.Coordinate
import org.example.assignment.model.Direction.EAST
import org.example.assignment.model.Direction.NORTH
import org.example.assignment.model.Movement
import org.example.assignment.utils.Given
import org.example.assignment.utils.Then
import org.example.assignment.utils.When
import org.example.assignment.utils.matcher.CoordinatesMatcher.Companion.coordinates
import org.example.assignment.utils.matcher.MovementsMatcher.Companion.movements
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus.OK

@SpringBootTest(webEnvironment = RANDOM_PORT)
class RobotControllerIT {

    @LocalServerPort
    private val port: Int = 0

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @Test
    fun locations() {
        Given {
            contentType(JSON)
            body(
                arrayOf(
                    Movement(direction = NORTH, steps = 3),
                    Movement(direction = EAST, steps = 2),
                )
            )
        } When {
            post(::locations.name)
        } Then {
            statusCode(OK.value())
            contentType(JSON)
            body(
                coordinates(
                    Coordinate(x = 0, y = 0),
                    Coordinate(x = 0, y = 3),
                    Coordinate(x = 2, y = 3),
                )
            )
        }
    }

    @Test
    fun moves() {
        Given {
            contentType(JSON)
            body(
                arrayOf(
                    Coordinate(x = 1, y = 3),
                    Coordinate(x = 2, y = 5),
                )
            )
        } When {
            post(::moves.name)
        } Then {
            statusCode(OK.value())
            contentType(JSON)
            body(
                movements(
                    Movement(direction = EAST, steps = 1),
                    Movement(direction = NORTH, steps = 2),
                )
            )
        }
    }
}


