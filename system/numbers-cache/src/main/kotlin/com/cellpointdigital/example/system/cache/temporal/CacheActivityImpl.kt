package com.cellpointdigital.example.system.cache.temporal

import com.cellpointdigital.example.process.activity.NumbersCacheActivity
import com.cellpointdigital.example.protobuf.model.IsPrimeRequest
import com.cellpointdigital.example.protobuf.service.NumbersCacheResponse
import com.cellpointdigital.example.system.cache.service.CacheService
import jakarta.inject.Singleton
import kotlinx.coroutines.runBlocking

@Singleton
class CacheActivityImpl(private val cacheService: CacheService) : NumbersCacheActivity {
    override fun isAPrimeInCache(number: Long): NumbersCacheResponse =
        runBlocking {
            cacheService.getPrime(IsPrimeRequest.newBuilder().setNumber(number).build())
        }

    override fun storePrimeInCache(number: Long): NumbersCacheResponse =
        runBlocking {
            cacheService.storePrime(IsPrimeRequest.newBuilder().setNumber(number).build())
        }

}