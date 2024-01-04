package com.cellpointdigital.example.numbers.api.temporal

import com.cellpointdigital.example.process.workflow.IS_PRIME_QUEUE
import com.cellpointdigital.example.process.workflow.IsPrimeWorkflow
import com.cellpointdigital.example.protobuf.model.IsPrimeRequest
import com.cellpointdigital.example.protobuf.model.IsPrimeResponse
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.client.WorkflowStub
import io.temporal.serviceclient.WorkflowServiceStubs
import jakarta.inject.Singleton

@Singleton
class TemporalServiceImpl(private val temporalConfig: TemporalConfig) : TemporalService {

    private var temporalClient: WorkflowClient =
        WorkflowClient.newInstance(WorkflowServiceStubs.newLocalServiceStubs())

    override fun isPrime(number: Long): IsPrimeResponse {
        val workflow = temporalClient.newWorkflowStub(
            IsPrimeWorkflow::class.java,
            WorkflowOptions {
                setTaskQueue(IS_PRIME_QUEUE)
            }
        )

        WorkflowClient.start(
            workflow::isPrime,
            IsPrimeRequest.newBuilder()
                .setNumber(number).build()
        )

        val untypedStub = WorkflowStub.fromTyped(workflow)

        return untypedStub.getResult(IsPrimeResponse::class.java)
    }
}