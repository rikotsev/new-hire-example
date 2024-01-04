package com.cellpointdigital.example.system.prime

import com.cellpointdigital.example.system.prime.temporal.IsPrimeCalcWorker
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.event.ApplicationStartupEvent
import jakarta.inject.Singleton

@Singleton
class ApplicationStartup(private val isPrimeCalcWorker: IsPrimeCalcWorker) :
ApplicationEventListener<ApplicationStartupEvent> {
    override fun onApplicationEvent(event: ApplicationStartupEvent?) {
        isPrimeCalcWorker.start()
    }
}