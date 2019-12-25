plugins {
    kotlin("jvm") version "1.3.61"
}

dependencies {
    api(project(":equalizer-core"))

    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8", version = "1.3.61")
}