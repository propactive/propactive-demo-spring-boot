package org.example.assignment.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.SEQUENCE
import jakarta.persistence.Id

@Entity(name = "movement_table")
data class Movement(
    @Id
    @Column(name = "movement_id")
    @GeneratedValue(strategy = SEQUENCE)
    val id: Long? = null,

    @Column(name = "movement_direction")
    @Enumerated(EnumType.ORDINAL)
    val direction: Direction,

    @Column(name = "movement_steps")
    val steps: Int,
)
