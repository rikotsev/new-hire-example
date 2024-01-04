package com.cellpointdigital.example.system.prime.temporal

import com.cellpointdigital.example.process.activity.CACHE_ACTIVITY
import com.cellpointdigital.example.process.activity.IS_PRIME_ACTIVITY
import com.cellpointdigital.example.process.activity.IsPrimeCalcActivity
import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory
import jakarta.inject.Singleton

@Singleton
class IsPrimeCalcWorker(private val isPrimeCalcActivity: IsPrimeCalcActivity) {

    fun start() {
        val client =
            WorkflowClient.newInstance(WorkflowServiceStubs.newLocalServiceStubs())

        val factory = WorkerFactory.newInstance(client)
        val worker = factory.newWorker(IS_PRIME_ACTIVITY)
        worker.registerActivitiesImplementations(isPrimeCalcActivity)

        factory.start()
    }

}