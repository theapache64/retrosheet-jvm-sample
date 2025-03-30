plugins {
    id("com.google.devtools.ksp") version "2.1.20-1.0.32"
    kotlin("jvm") version "2.1.20"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20"
    id("de.jensklingenberg.ktorfit") version "2.4.1"
}

group = "io.github.theapache64"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

    // Kotlinx.serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

    // Ktorfit
    implementation("de.jensklingenberg.ktorfit:ktorfit-lib:2.4.1")
    implementation("io.ktor:ktor-client-content-negotiation:3.1.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.1.1")

    // Retrosheet
    implementation("io.github.theapache64:retrosheet:3.0.0-alpha03")

    // SLF4J No-op
    implementation("org.slf4j:slf4j-nop:2.0.7")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}