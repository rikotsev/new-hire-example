package com.cellpointdigital.example.process.config

sealed interface TemporalConfig {
    fun frontend(): String
    fun namespace(): String

    data object FromEnvironment : TemporalConfig {
        override fun frontend(): String {
            return System.getenv("TEMPORAL_FRONTEND") ?: "localhost:7233"
        }

        override fun namespace(): String {
//            return System.getenv("TEMPORAL_NAMESPACE") ?: "default"
            return "default"
        }
    }
}

sealed interface gRPCConfig {
    fun endpoints(): List<gRPCEndpoint>
}

data class gRPCEndpoint(val host: String, val port: Int) {

}