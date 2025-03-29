plugins {
    id("com.google.devtools.ksp") version "2.1.20-1.0.32"
    kotlin("jvm") version "2.1.20"
}

group = "io.github.theapache64"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    maven { url = uri("https://jitpack.io") }
}

dependencies {

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.15.2")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.2")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    // Retrosheet
    implementation("com.github.theapache64:retrosheet:2.0.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}