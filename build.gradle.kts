plugins {
    kotlin("jvm") version "2.4.10"
    application
}

group = "com.danylom73"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(23)
}

application {
    mainClass.set("com.danylom73.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
