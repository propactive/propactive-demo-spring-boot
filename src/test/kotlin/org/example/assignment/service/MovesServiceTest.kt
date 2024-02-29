package org.example.assignment.service

import io.kotest.matchers.collections.shouldBeEmpty
import kotlin.random.Random
import org.example.assignment.model.Coordinate
import org.junit.jupiter.api.Test

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
}