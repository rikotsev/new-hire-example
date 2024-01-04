package com.cellpointdigital.example.system.cache.temporal

import com.cellpointdigital.example.process.activity.CACHE_ACTIVITY
import com.cellpointdigital.example.process.activity.NumbersCacheActivity
import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory
import jakarta.inject.Singleton

@Singleton
class CacheActivityWorker(private val cacheActivity: NumbersCacheActivity) {

    fun start() {
        val client =
            WorkflowClient.newInstance(WorkflowServiceStubs.newLocalServiceStubs())

        val factory = WorkerFactory.newInstance(client)
        val worker = factory.newWorker(CACHE_ACTIVITY)
        worker.registerActivitiesImplementations(cacheActivity)

        println("Started activity listener!")

        factory.start()
    }

}