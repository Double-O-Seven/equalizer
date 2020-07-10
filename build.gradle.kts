/*
 * Copyright (C) 2020 Adrian-Philipp Leuenberger
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
    kotlin("jvm") apply false
    `java-library`
    `code-coverage-report`
    id("com.palantir.git-version") version "0.12.2"
}

val gitVersion: Closure<String> by extra

allprojects {
    group = "ch.leadrian.equalizer"

    version = gitVersion()

    repositories {
        jcenter()
    }
}

subprojects {

    pluginManager.withPlugin("java-library") {

        java {
            withSourcesJar()
            withJavadocJar()
        }

        dependencies {
            testImplementation(platform("org.junit:junit-bom:5.6.1"))
            testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
            testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-params")
            testImplementation(group = "org.assertj", name = "assertj-core", version = "3.15.0")

            testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine")
        }

        tasks.test {
            useJUnitPlatform()
        }
    }
}
