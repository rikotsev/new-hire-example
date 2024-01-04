package com.cellpointdigital.example.system.cache.service

import com.cellpointdigital.example.protobuf.model.IsPrimeRequest
import com.cellpointdigital.example.protobuf.service.CachedValuesRequest
import com.cellpointdigital.example.protobuf.service.CachedValuesResponse
import com.cellpointdigital.example.protobuf.service.NumbersCacheResponse
import com.cellpointdigital.example.protobuf.service.NumbersCacheServiceGrpcKt
import com.cellpointdigital.example.system.cache.repository.NumbersRepository
import jakarta.inject.Singleton

@Singleton
open class CacheService(private val repository: NumbersRepository)
    : NumbersCacheServiceGrpcKt.NumbersCacheServiceCoroutineImplBase() {

    override suspend fun getPrime(request: IsPrimeRequest): NumbersCacheResponse {
        return NumbersCacheResponse.newBuilder()
            .setIsCached(repository.contains(request.number))
            .build()
    }

    override suspend fun storePrime(request: IsPrimeRequest): NumbersCacheResponse {
        repository.add(request.number)

        return NumbersCacheResponse.newBuilder()
            .setIsCached(true).build()
    }

    override suspend fun getCachedValues(request: CachedValuesRequest): CachedValuesResponse {
        val builder = CachedValuesResponse.newBuilder()
        repository.all().forEach { number -> builder.addValues(number) }

        return builder.build()
    }
}