plugins {
    kotlin("jvm") version "1.8.0"
}

group = "fr.valentin.api.event"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

tasks.named<Jar>("jar") {
    finalizedBy("exportFiles")
}

tasks.register<Copy>("exportFiles") {
    from(layout.buildDirectory.dir("libs"))
    include("**/*.*")
    into(layout.buildDirectory.dir("../../PluginAPI/EmptyTerminal/dependencies"))
}