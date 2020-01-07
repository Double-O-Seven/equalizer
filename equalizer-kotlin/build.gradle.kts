plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":equalizer-core"))

    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
}