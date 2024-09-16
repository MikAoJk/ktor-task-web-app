import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val kotlinVersion = "2.0.20"
val logbackVersion = "1.4.14"
val ktorVersion = "3.0.0-rc-1"

// override dependencies
val commonsCodecVersion = "1.17.1"

val javaVersion = JvmTarget.JVM_21

plugins {
    kotlin("jvm") version "2.0.20"
    id("application")
}

group = "io.github.mikaojk"
version = "0.0.1"

application {
    mainClass.set("io.github.mikaojk.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-thymeleaf-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-config-yaml:$ktorVersion")

    testImplementation("io.ktor:ktor-server-test-host-jvm:$ktorVersion")
    constraints {
        testImplementation("commons-codec:commons-codec:$commonsCodecVersion") {
            because("override transient from io.ktor:ktor-server-test-host-jvm")
        }
    }
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}

kotlin {
    compilerOptions {
        jvmTarget.set(javaVersion)
    }
}