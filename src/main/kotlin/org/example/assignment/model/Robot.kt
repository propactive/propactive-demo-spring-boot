package org.example.assignment.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity(name = "robot_table")
data class Robot(
    @Id
    @Column(name = "robot_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    val id: UUID? = null,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST])
    @Column(name = "robot_movements")
    val movements : List<Movement>,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST])
    @Column(name = "robot_coordinates")
    val coordinates: List<Coordinate>,
)
