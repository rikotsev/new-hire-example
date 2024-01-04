package com.cellpointdigital.example.numbers.api.temporal

import com.cellpointdigital.example.protobuf.model.IsPrimeResponse

interface TemporalService {
    fun isPrime(number: Long): IsPrimeResponse
}