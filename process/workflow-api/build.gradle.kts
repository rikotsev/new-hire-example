plugins {
    kotlin("jvm") version "1.9.21"
    id("maven-publish")
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
}

version = "1.0.0"
group = "com.cellpointdigital.example.process"

dependencies {
    // This project does not use Micronaut, but align versions with Micronaut, so they are aligned across all Kotlin
    // projects in Phalanx.
    implementation(platform("io.micronaut.platform:micronaut-platform:4.2.1"))

    implementation("io.temporal:temporal-sdk:1.22.3")
    implementation("io.temporal:temporal-kotlin:1.22.3")

    implementation("io.grpc:grpc-kotlin-stub")
    implementation("com.cellpointdigital.example.protobuf:protobuf:unspecified")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        publications {
            create<MavenPublication>("maven") {
                groupId = project.group.toString()
                artifactId = project.name
                version = project.version.toString()
                from(components["java"])
            }
        }
    }
}