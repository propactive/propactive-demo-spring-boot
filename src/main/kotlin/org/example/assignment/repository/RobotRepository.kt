package org.example.assignment.repository

import java.util.UUID
import org.example.assignment.model.Robot
import org.springframework.data.jpa.repository.JpaRepository

interface RobotRepository: JpaRepository<Robot, UUID>
