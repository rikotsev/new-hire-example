import com.google.protobuf.gradle.*

plugins {
    kotlin("jvm") version "1.9.21"
    id("com.google.protobuf") version "0.9.4"
    id("com.diffplug.spotless") version "6.23.3"
    id("maven-publish")
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
}

group = "com.cellpointdigital.example.protobuf"

dependencies {
    api("io.grpc:grpc-kotlin-stub")
    api("build.buf:protovalidate:0.1.9")

    // This library does not use Micronaut and can be used by non-Micronaut projects. But we want it to use the same
    // dependency versions as Micronaut to work as seamlessly as possible with our Micronaut services, so here we apply
    // the Micronaut platform BOM. See also the section "Micronaut Framework BOM" in:
    // https://micronaut.io/2023/05/09/upgrade-to-micronaut-framework-4-0-0/
    implementation(platform("io.micronaut.platform:micronaut-platform:4.2.1"))
    implementation("javax.annotation:javax.annotation-api")
    implementation("com.google.protobuf:protobuf-kotlin")
    implementation("com.google.protobuf:protobuf-java-util")
    implementation("com.google.protobuf:protobuf-java")
    implementation("io.grpc:grpc-stub")
    implementation("io.grpc:grpc-protobuf")
}

sourceSets {
    main {
        java {
            srcDir(layout.buildDirectory.dir("generated/source/proto/main/kotlin"))
            srcDir(layout.buildDirectory.dir("generated/source/proto/main/java"))
            srcDir(layout.buildDirectory.dir("generated/source/proto/main/grpc"))
            srcDir(layout.buildDirectory.dir("generated/source/proto/main/grpckt"))
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.1"
    }
    plugins{
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.60.1"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc")
                id("grpckt")
            }
            task.builtins {
                id("kotlin")
            }
        }
    }
}

spotless {
    kotlin {
        targetExclude("build/**")
        ktfmt("0.46").kotlinlangStyle().configure { optionsConfiguration ->
            optionsConfiguration.setMaxWidth(120)
        }

    }
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