import com.google.protobuf.gradle.*
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.21"
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.2.1"
    id("com.google.protobuf") version "0.9.2"
}

version = "0.1"
group = "numbers.cache"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut:micronaut-discovery-core")
    implementation("io.micronaut.grpc:micronaut-grpc-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.yaml:snakeyaml")
    implementation("com.cellpointdigital.example.protobuf:protobuf:unspecified")
    implementation("com.cellpointdigital.example.process:workflow-api")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("io.temporal:temporal-sdk:1.22.3")
    implementation("io.temporal:temporal-kotlin:1.22.3")
}


application {
    mainClass.set("com.cellpointdigital.example.system.cache.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}
kotlin {
    jvmToolchain(17)
}


sourceSets {
    main {
        java {
            srcDirs("build/generated/source/proto/main/java")
        }
    }
}


micronaut {
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("numbers.cache.*")
    }
}



