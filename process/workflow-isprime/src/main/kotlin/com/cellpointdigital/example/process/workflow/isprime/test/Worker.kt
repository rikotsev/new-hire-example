package com.cellpointdigital.example.process.workflow.isprime.test

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory

fun main() {
    val service = WorkflowServiceStubs.newLocalServiceStubs()
    val client = WorkflowClient.newInstance(service)
    val factory = WorkerFactory.newInstance(client)

    val worker = factory.newWorker(QUEUE)
    worker.registerWorkflowImplementationTypes(MyWorkflowImpl::class.java)

    factory.start()
}