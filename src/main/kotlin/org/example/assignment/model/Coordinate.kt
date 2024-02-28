package org.example.assignment.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.SEQUENCE
import jakarta.persistence.Id

@Entity(name = "coordinate_table")
data class Coordinate(
    @Id
    @Column(name = "coordinate_id")
    @GeneratedValue(strategy = SEQUENCE)
    val id: Long? = null,

    @Column(name = "coordinate_x")
    val x : Int,

    @Column(name = "coordinate_y")
    val y : Int,
) {
    companion object {
        fun initial() = listOf(Coordinate(x = 0, y = 0))
    }
}
