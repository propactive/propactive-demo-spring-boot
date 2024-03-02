package org.example.assignment.utils.matcher

import io.restassured.path.json.JsonPath
import org.example.assignment.model.Coordinate
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class CoordinatesMatcher(
    private val expectedCoordinates: List<Coordinate>
) : TypeSafeMatcher<String>() {
    override fun describeTo(description: Description) {
        expectedCoordinates
            .joinToString(separator = ",", prefix = "[", postfix = "]") { "{\"x\":${it.x},\"y\":${it.y}}" }
            .let(description::appendText)
    }

    override fun matchesSafely(item: String): Boolean {
        val jsonPath = JsonPath.from(item)
        val listSize = jsonPath.getList<Any>("$").size
        if (listSize != expectedCoordinates.size) return false
        expectedCoordinates.forEachIndexed { index, coordinate ->
            val x = jsonPath.getInt("[$index].x")
            val y = jsonPath.getInt("[$index].y")
            if (x != coordinate.x || y != coordinate.y) return false
        }
        return true
    }

    companion object {
        @JvmStatic
        fun coordinates(vararg coordinates: Coordinate) =
            CoordinatesMatcher(coordinates.toList())
    }
}