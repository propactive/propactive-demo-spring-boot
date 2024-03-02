package org.example.assignment.utils.matcher

import io.restassured.path.json.JsonPath
import org.example.assignment.model.Movement
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class MovementsMatcher(
    private val expectedMovements: List<Movement>
) : TypeSafeMatcher<String>() {

    override fun describeTo(description: Description) {
        expectedMovements
            .joinToString(separator = ",", prefix = "[", postfix = "]") { "{\"direction\":\"${it.direction.name}\",\"steps\":${it.steps}}" }
            .let(description::appendText)
    }

    override fun matchesSafely(item: String): Boolean {
        val jsonPath = JsonPath.from(item)
        val movementsSize = jsonPath.getList<Any>("$").size
        if (movementsSize != expectedMovements.size) return false
        expectedMovements.forEachIndexed { index, movement ->
            val direction = jsonPath.getString("[$index].direction")
            val steps = jsonPath.getInt("[$index].steps")
            if (direction != movement.direction.name || steps != movement.steps) return false
        }
        return true
    }

    companion object {
        fun movements(vararg movements: Movement): MovementsMatcher {
            return MovementsMatcher(movements.toList())
        }
    }
}