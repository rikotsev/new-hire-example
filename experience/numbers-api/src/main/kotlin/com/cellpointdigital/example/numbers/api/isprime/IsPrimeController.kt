package com.cellpointdigital.example.numbers.api.isprime

import com.cellpointdigital.example.numbers.api.temporal.TemporalService
import io.micronaut.http.annotation.*
import io.micronaut.serde.annotation.Serdeable

@Controller
class IsPrimeController(private val temporalService: TemporalService) {

    @Post("/is_prime")
    @Produces("application/json", "application/problem+json")
    @Consumes("application/json")
    fun isPrime(@Body request: NumberRequest): NumberResponse {
        val response = temporalService.isPrime(request.number)

        return NumberResponse(request.number, response.time, response.isPrime, response.isCached)
    }

}

@Serdeable.Deserializable
data class NumberRequest(val number: Long)

@Serdeable.Serializable
data class NumberResponse(val number: Long, val time: Int, val isPrime: Boolean, val isCached: Boolean)