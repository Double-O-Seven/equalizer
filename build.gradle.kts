/*
 * Copyright (C) 2019 Adrian-Philipp Leuenberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import groovy.lang.Closure

plugins {
    `java-library`
    id("com.palantir.git-version") version "0.12.0-rc2"
}

val junitVersion = "5.5.1"
val kotlinVersion = "1.3.61"

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
