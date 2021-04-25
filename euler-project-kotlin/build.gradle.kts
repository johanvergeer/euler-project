import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
    application
}

group = "me.jve23941"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val spekVersion = "2.0.15"
val kotlinVersion = "1.4.21"
val kotestVersion = "4.4.2"

dependencies {
    testImplementation(kotlin("test-junit"))
    // some version of Kotlin

//    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
//    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
//    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.23.1")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")

    // spek requires kotlin-reflect, can be omitted if already in the classpath
//    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
