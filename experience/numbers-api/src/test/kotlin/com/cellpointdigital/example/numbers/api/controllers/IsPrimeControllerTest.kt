package com.cellpointdigital.example.numbers.api.controllers

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@MicronautTest
class IsPrimeControllerTest {

    lateinit var server: EmbeddedServer

    @Nested
    inner class IsPrime {

        @Test
        fun `is prime for a prime number returns true`() {
        }

        @Test
        fun `is prime for a not prime number returns false`() {

        }

    }

}