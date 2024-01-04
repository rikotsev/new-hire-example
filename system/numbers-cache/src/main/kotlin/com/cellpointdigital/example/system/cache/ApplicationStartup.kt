package com.cellpointdigital.example.system.cache

import com.cellpointdigital.example.system.cache.temporal.CacheActivityWorker
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.event.ApplicationStartupEvent
import jakarta.inject.Singleton

@Singleton
class ApplicationStartup(private val cacheActivityWorker: CacheActivityWorker) :
    ApplicationEventListener<ApplicationStartupEvent> {
    override fun onApplicationEvent(event: ApplicationStartupEvent?) {
        cacheActivityWorker.start()
    }
}