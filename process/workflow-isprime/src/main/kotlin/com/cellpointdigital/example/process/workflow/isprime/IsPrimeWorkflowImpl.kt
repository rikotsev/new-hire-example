package com.cellpointdigital.example.process.workflow.isprime

import com.cellpointdigital.example.process.activity.CACHE_ACTIVITY
import com.cellpointdigital.example.process.activity.IS_PRIME_ACTIVITY
import com.cellpointdigital.example.process.activity.IsPrimeCalcActivity
import com.cellpointdigital.example.process.activity.NumbersCacheActivity
import com.cellpointdigital.example.process.workflow.IsPrimeWorkflow
import com.cellpointdigital.example.protobuf.model.IsPrimeRequest
import com.cellpointdigital.example.protobuf.model.IsPrimeResponse
import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.Workflow
import java.time.Duration

class IsPrimeWorkflowImpl : IsPrimeWorkflow {

    private var status: String = "waiting"

    private val cacheActivity: NumbersCacheActivity =
        Workflow.newActivityStub(NumbersCacheActivity::class.java,
            ActivityOptions {
                setStartToCloseTimeout(Duration.ofSeconds(10))
                setRetryOptions(RetryOptions { setMaximumAttempts(3) })
                setTaskQueue(CACHE_ACTIVITY)
            })
    private val isPrimeCalcActivity: IsPrimeCalcActivity =
        Workflow.newActivityStub(IsPrimeCalcActivity::class.java,
            ActivityOptions {
                setStartToCloseTimeout(Duration.ofSeconds(10))
                setRetryOptions(RetryOptions { setMaximumAttempts(3) })
                setTaskQueue(IS_PRIME_ACTIVITY)
            })

    override fun isPrime(request: IsPrimeRequest): IsPrimeResponse {
        val number = request.number

        val cacheResponse = cacheActivity.isAPrimeInCache(number)

        if (cacheResponse.isCached) {
            return IsPrimeResponse.newBuilder()
                .setIsPrime(true)
                .setIsCached(true)
                .setNumber(number)
                .setTime(0)
                .build()
        }

        val calcResponse = isPrimeCalcActivity.isPrimeCalc(number)

        if (calcResponse.isPrime) {
            cacheActivity.storePrimeInCache(number)

            return IsPrimeResponse.newBuilder()
                .setIsPrime(true)
                .setIsCached(false)
                .setNumber(number)
                .setTime(0)
                .build()
        }

        return IsPrimeResponse.newBuilder()
            .setIsPrime(false)
            .setIsCached(false)
            .setNumber(number)
            .setTime(0)
            .build()
    }

    override fun status(): String {
        return status
    }
}