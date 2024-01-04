package com.cellpointdigital.example.system.prime.service

import com.cellpointdigital.example.protobuf.model.IsPrimeRequest
import com.cellpointdigital.example.protobuf.service.IsPrimeCalcResponse
import com.cellpointdigital.example.protobuf.service.IsPrimeCalcServiceGrpcKt
import jakarta.inject.Singleton
import kotlin.math.sqrt

@Singleton
open class PrimeCalculatorService : IsPrimeCalcServiceGrpcKt.IsPrimeCalcServiceCoroutineImplBase() {

    override suspend fun get(request: IsPrimeRequest): IsPrimeCalcResponse {
        val number = request.number
        val root = sqrt(number.toDouble()).toLong()

        for(i in 2..root) {
            if(number % i == 0L) {
                return IsPrimeCalcResponse.newBuilder()
                    .setIsPrime(false)
                    .setTime(1)
                    .build()
            }
        }

        return IsPrimeCalcResponse.newBuilder()
            .setIsPrime(true)
            .setTime(1)
            .build()
    }

}