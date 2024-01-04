package com.cellpointdigital.example.process.activity

import com.cellpointdigital.example.protobuf.service.NumbersCacheResponse
import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod


const val CACHE_ACTIVITY = "cache-activity"

@ActivityInterface
interface NumbersCacheActivity {
    @ActivityMethod
    fun isAPrimeInCache(number: Long): NumbersCacheResponse

    @ActivityMethod
    fun storePrimeInCache(number: Long): NumbersCacheResponse
}