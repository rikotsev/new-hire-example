package com.cellpointdigital.example.process.activity

import com.cellpointdigital.example.protobuf.service.IsPrimeCalcResponse
import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

const val IS_PRIME_ACTIVITY = "is-prime-activity"

@ActivityInterface
interface IsPrimeCalcActivity {

    @ActivityMethod
    fun isPrimeCalc(number: Long): IsPrimeCalcResponse
}