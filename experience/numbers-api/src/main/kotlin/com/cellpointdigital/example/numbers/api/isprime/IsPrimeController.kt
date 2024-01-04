package com.cellpointdigital.example.numbers.api.isprime

import com.cellpointdigital.example.numbers.api.temporal.TemporalService
import com.cellpointdigital.example.protobuf.service.CachedValuesRequest
import com.cellpointdigital.example.protobuf.service.NumbersCacheServiceGrpcKt
import io.micronaut.http.annotation.*
import io.micronaut.serde.annotation.Serdeable

@Controller
class IsPrimeController(private val temporalService: TemporalService,
    private val cacheService: NumbersCacheServiceGrpcKt.NumbersCacheServiceCoroutineStub) {

    @Post("/is_prime")
    @Produces("application/json", "application/problem+json")
    @Consumes("application/json")
    fun isPrime(@Body request: NumberRequest): NumberResponse {
        val response = temporalService.isPrime(request.number)

        return NumberResponse(request.number, response.time, response.isPrime, response.isCached)
    }

    @Get("/cached_primes")
    @Produces("application/json", "application/problem+json")
    suspend fun cachedPrimes(): CachedPrimesResponse {
        val response = cacheService.getCachedValues(CachedValuesRequest.newBuilder().setSize(10).build())

        return CachedPrimesResponse(HashSet(response.valuesList))
    }

}

@Serdeable.Deserializable
data class NumberRequest(val number: Long)

@Serdeable.Serializable
data class NumberResponse(val number: Long, val time: Int, val isPrime: Boolean, val isCached: Boolean)

@Serdeable.Serializable
data class CachedPrimesResponse(val values: Set<Long>)