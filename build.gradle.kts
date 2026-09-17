plugins {
    kotlin("jvm") version "2.4.10"
    application
}

group = "com.danylom73"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("com.danylom73.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
