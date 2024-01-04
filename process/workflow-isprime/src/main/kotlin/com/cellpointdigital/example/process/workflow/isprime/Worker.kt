package com.cellpointdigital.example.process.workflow.isprime

import com.cellpointdigital.example.process.config.TemporalConfig
import com.cellpointdigital.example.process.workflow.ExtendedDataConverter
import com.cellpointdigital.example.process.workflow.IS_PRIME_QUEUE
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.common.converter.GlobalDataConverter
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory

fun main() {

    //GlobalDataConverter.register(ExtendedDataConverter())

    val client =
        WorkflowClient.newInstance(WorkflowServiceStubs.newLocalServiceStubs())

    val factory = WorkerFactory.newInstance(client)
    val worker = factory.newWorker(IS_PRIME_QUEUE)

    worker.registerWorkflowImplementationTypes(
        IsPrimeWorkflowImpl::class.java
    )

    factory.start()
}