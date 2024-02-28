package org.example.assignment

import io.kotest.assertions.throwables.shouldNotThrowAny
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ApplicationTest {
    @Nested
    inner class Smoke {
        @Test
        fun `application should run with no exceptions`() {
            shouldNotThrowAny {
                Application.main(emptyArray())
            }
        }
    }
}