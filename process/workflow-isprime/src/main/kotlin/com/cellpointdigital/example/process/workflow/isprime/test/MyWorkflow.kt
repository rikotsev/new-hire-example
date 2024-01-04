package com.cellpointdigital.example.process.workflow.isprime.test

import com.cellpointdigital.example.protobuf.model.IsPrimeRequest
import com.cellpointdigital.example.protobuf.model.IsPrimeResponse
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

const val QUEUE = "task_queue"

@WorkflowInterface
interface MyWorkflow {

    @WorkflowMethod
    fun justSleep(seconds: IsPrimeRequest): IsPrimeResponse

}