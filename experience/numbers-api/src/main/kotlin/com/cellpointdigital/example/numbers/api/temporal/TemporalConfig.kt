package com.cellpointdigital.example.numbers.api.temporal

import io.micronaut.context.annotation.ConfigurationInject
import io.micronaut.context.annotation.ConfigurationProperties

@Suppress("MnInjectionPoints")
@ConfigurationProperties("temporal")
data class TemporalConfig @ConfigurationInject constructor(val frontend: String, val namespace: String) {
}