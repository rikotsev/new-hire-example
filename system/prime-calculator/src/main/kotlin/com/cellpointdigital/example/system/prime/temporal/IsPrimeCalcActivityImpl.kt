package com.cellpointdigital.example.system.prime.temporal

import com.cellpointdigital.example.process.activity.IsPrimeCalcActivity
import com.cellpointdigital.example.protobuf.model.IsPrimeRequest
import com.cellpointdigital.example.protobuf.service.IsPrimeCalcResponse
import com.cellpointdigital.example.system.prime.service.PrimeCalculatorService
import jakarta.inject.Singleton
import kotlinx.coroutines.runBlocking

@Singleton
class IsPrimeCalcActivityImpl(private val primeCalculatorService: PrimeCalculatorService) : IsPrimeCalcActivity {
    override fun isPrimeCalc(number: Long): IsPrimeCalcResponse = runBlocking {
        primeCalculatorService.get(IsPrimeRequest.newBuilder().setNumber(number).build())
    }
}