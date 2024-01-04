package com.cellpointdigital.example.numbers.api

import com.cellpointdigital.example.protobuf.service.NumbersCacheServiceGrpcKt
import io.grpc.Channel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import jakarta.inject.Singleton

@Factory
class BeanFactory {

    @Singleton
    fun cacheService(
        @GrpcChannel("cache-service") channel: Channel
    ): NumbersCacheServiceGrpcKt.NumbersCacheServiceCoroutineStub =
        NumbersCacheServiceGrpcKt.NumbersCacheServiceCoroutineStub(channel)

}