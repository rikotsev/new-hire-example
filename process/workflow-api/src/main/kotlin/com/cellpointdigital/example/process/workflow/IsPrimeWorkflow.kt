package com.cellpointdigital.example.process.workflow

import com.cellpointdigital.example.protobuf.model.IsPrimeRequest
import com.cellpointdigital.example.protobuf.model.IsPrimeResponse
import io.temporal.workflow.QueryMethod
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

const val IS_PRIME_QUEUE = "is_prime_queue"

@WorkflowInterface
interface IsPrimeWorkflow {

    @WorkflowMethod
    fun isPrime(request: IsPrimeRequest): IsPrimeResponse

    @QueryMethod
    fun status(): String

}