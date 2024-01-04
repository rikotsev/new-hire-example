package com.cellpointdigital.example.process.workflow.isprime.test

import com.cellpointdigital.example.protobuf.model.IsPrimeRequest
import com.cellpointdigital.example.protobuf.model.IsPrimeResponse
import kotlin.math.sqrt

class MyWorkflowImpl : MyWorkflow {
    override fun justSleep(param: IsPrimeRequest): IsPrimeResponse {
        val number = param.number
        println("Will check if $number is prime")

        val root = sqrt(number.toDouble()).toLong()

        println("root = $root")

        for(i in 2..root) {
            if(number % i == 0L) {
                return IsPrimeResponse.newBuilder()
                    .setIsPrime(false)
                    .setNumber(number)
                    .setIsCached(false)
                    .setTime(0)
                    .build()
            }
        }

        return IsPrimeResponse.newBuilder()
            .setIsPrime(true)
            .setNumber(number)
            .setIsCached(false)
            .setTime(0)
            .build()
    }
}