package com.cellpointdigital.example.process.workflow.isprime.test

import com.cellpointdigital.example.protobuf.model.IsPrimeRequest
import com.cellpointdigital.example.protobuf.model.IsPrimeResponse
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.client.WorkflowStub
import io.temporal.serviceclient.WorkflowServiceStubs

fun main() {
    val service = WorkflowServiceStubs.newLocalServiceStubs()
    val client = WorkflowClient.newInstance(service)

    val workflow = client.newWorkflowStub(
        MyWorkflow::class.java,
        WorkflowOptions {
            setTaskQueue(QUEUE)
        }
    )

    WorkflowClient.start(workflow::justSleep, IsPrimeRequest.newBuilder()
        .setNumber(11).build())

    val untypedWorkflow = WorkflowStub.fromTyped(workflow)

    val result = untypedWorkflow.getResult(IsPrimeResponse::class.java)
    println("${result.number} is prime = ${result.isPrime} and is cached = ${result.isCached} ")
}