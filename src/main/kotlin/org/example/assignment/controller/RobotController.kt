package org.example.assignment.controller

import org.example.assignment.model.Movement
import org.example.assignment.model.Robot
import org.example.assignment.repository.RobotRepository
import org.example.assignment.service.LocationsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class RobotController(
    @Autowired val robotRepository: RobotRepository,
) {
    @PostMapping("/locations")
    fun deriveCoordinates(@RequestBody(required = true) movements: Array<Movement>) = with(movements.toList()) {
        robotRepository
            .save(Robot(movements = this, coordinates = LocationsService.derive(this)))
            .coordinates
    }
}