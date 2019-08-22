import groovy.lang.Closure

plugins {
    `java-library`
    id("com.palantir.git-version") version "0.12.0-rc2"
}

val junitVersion = "5.5.1"

val gitVersion: Closure<String> by extra

allprojects {
    group = "ch.leadrian.equalizer"

    version = gitVersion()
}

subprojects {
    apply(plugin = "java-library")

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
        testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-params", version = junitVersion)
        testImplementation(group = "org.assertj", name = "assertj-core", version = "3.12.1")

        testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)
    }

    tasks {
        test {
            useJUnitPlatform()
        }
    }
}
